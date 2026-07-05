#version 330

uniform sampler2D InSampler;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform MotionBlurConfig {
    float Strength;
    float Radius;
};

in vec2 texCoord;

out vec4 fragColor;

// Single-pass radial smear: 8 taps toward the screen center, mixed Strength (~40%) over the
// scene. Reads as motion blur without needing any frame history.
void main() {
    vec2 center = vec2(0.5, 0.5);
    // Aspect-corrected direction so the smear is circularly symmetric on screen.
    vec2 aspect = vec2(OutSize.x / OutSize.y, 1.0);
    vec2 dir = (texCoord - center) * aspect;
    float dist = length(dir);
    vec2 sampleStep = (dist > 0.0001 ? dir / dist : vec2(0.0)) * Radius * dist / aspect;

    vec4 base = texture(InSampler, texCoord);
    vec4 sum = vec4(0.0);
    for (int i = 1; i <= 8; i++) {
        float t = float(i) / 8.0;
        sum += texture(InSampler, texCoord - sampleStep * t);
    }
    sum /= 8.0;

    fragColor = vec4(mix(base.rgb, sum.rgb, Strength), 1.0);
}
