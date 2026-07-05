package net.sonic0810.copperinferno.feature.statue.client;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.sonic0810.copperinferno.feature.statue.PlayerStatueFeature;
import net.sonic0810.copperinferno.feature.statue.SetStatueNamePayload;

/**
 * Simple name-entry screen for the copper player statue: one text field and a Done button. On
 * confirm it sends a {@link SetStatueNamePayload} to the server.
 */
public class CopperPlayerStatueScreen extends Screen {
	private static final Text TITLE = Text.translatable("screen.copper_inferno.copper_player_statue.title");
	private static final Text NAME_LABEL = Text.translatable("screen.copper_inferno.copper_player_statue.name_label");

	private final BlockPos statuePos;
	private TextFieldWidget nameField;

	public CopperPlayerStatueScreen(BlockPos statuePos) {
		super(TITLE);
		this.statuePos = statuePos;
	}

	@Override
	protected void init() {
		int centerX = this.width / 2;
		int centerY = this.height / 2;
		this.nameField = new TextFieldWidget(this.textRenderer, centerX - 100, centerY - 20, 200, 20, NAME_LABEL);
		this.nameField.setMaxLength(PlayerStatueFeature.MAX_NAME_LENGTH);
		this.addDrawableChild(this.nameField);
		this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.done())
				.dimensions(centerX - 100, centerY + 10, 200, 20)
				.build());
		this.setInitialFocus(this.nameField);
	}

	private void done() {
		String name = this.nameField.getText().trim();
		if (!name.isEmpty()) {
			ClientPlayNetworking.send(new SetStatueNamePayload(this.statuePos, name));
		}
		this.close();
	}

	@Override
	public boolean keyPressed(KeyInput input) {
		if (input.key() == GLFW.GLFW_KEY_ENTER || input.key() == GLFW.GLFW_KEY_KP_ENTER) {
			this.done();
			return true;
		}
		return super.keyPressed(input);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.render(context, mouseX, mouseY, deltaTicks);
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFFFF);
		context.drawTextWithShadow(this.textRenderer, NAME_LABEL, this.width / 2 - 100, this.height / 2 - 32, 0xFFA0A0A0);
	}

	@Override
	public boolean shouldPause() {
		return false;
	}
}
