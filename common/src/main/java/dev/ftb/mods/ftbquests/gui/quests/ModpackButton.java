package dev.ftb.mods.ftbquests.gui.quests;

import com.feed_the_beast.mods.ftbguilibrary.utils.MouseButton;
import com.feed_the_beast.mods.ftbguilibrary.utils.TooltipList;
import com.feed_the_beast.mods.ftbguilibrary.widget.GuiHelper;
import com.feed_the_beast.mods.ftbguilibrary.widget.Panel;
import com.feed_the_beast.mods.ftbguilibrary.widget.Theme;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.gui.RewardNotificationsScreen;
import dev.ftb.mods.ftbquests.net.MessageClaimAllRewards;
import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * @author LatvianModder
 */
public class ModpackButton extends TabButton {
	public ModpackButton(Panel panel) {
		super(panel, TextComponent.EMPTY, ClientQuestFile.INSTANCE.getIcon());
		title = questScreen.file.getTitle();
	}

	@Override
	public void onClicked(MouseButton button) {
		if (questScreen.file.self.hasUnclaimedRewards(Minecraft.getInstance().player.getUUID(), questScreen.file)) {
			playClickSound();
			new RewardNotificationsScreen().openGui();
			new MessageClaimAllRewards().sendToServer();
		}
	}

	@Override
	public void addMouseOverText(TooltipList list) {
		super.addMouseOverText(list);

		if (questScreen.file.self.hasUnclaimedRewards(Minecraft.getInstance().player.getUUID(), questScreen.file)) {
			list.blankLine();
			list.add(new TranslatableComponent("ftbquests.gui.collect_rewards").withStyle(ChatFormatting.GOLD));
		}
	}

	@Override
	public void draw(PoseStack matrixStack, Theme theme, int x, int y, int w, int h) {
		super.draw(matrixStack, theme, x, y, w, h);

		if (questScreen.file.self.hasUnclaimedRewards(Minecraft.getInstance().player.getUUID(), questScreen.file)) {
			GuiHelper.setupDrawing();
			int s = w / 2;//(int) (treeGui.getZoom() / 2 * quest.size);
			matrixStack.pushPose();
			matrixStack.translate(x + w - s, y, 200);
			ThemeProperties.ALERT_ICON.get(questScreen.file).draw(matrixStack, 0, 0, s, s);
			matrixStack.popPose();
		}
	}
}