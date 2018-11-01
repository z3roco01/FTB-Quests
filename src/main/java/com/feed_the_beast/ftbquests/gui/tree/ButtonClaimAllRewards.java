package com.feed_the_beast.ftbquests.gui.tree;

import com.feed_the_beast.ftblib.lib.gui.GuiHelper;
import com.feed_the_beast.ftblib.lib.gui.GuiIcons;
import com.feed_the_beast.ftblib.lib.gui.Panel;
import com.feed_the_beast.ftblib.lib.util.misc.MouseButton;
import com.feed_the_beast.ftbquests.client.ClientQuestFile;
import com.feed_the_beast.ftbquests.net.MessageClaimAllRewards;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestChapter;
import com.feed_the_beast.ftbquests.quest.reward.QuestReward;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * @author LatvianModder
 */
public class ButtonClaimAllRewards extends ButtonTab
{
	public ButtonClaimAllRewards(Panel panel)
	{
		super(panel, I18n.format("ftbquests.reward.claim_all"), GuiIcons.MONEY_BAG);
	}

	@Override
	public void addMouseOverText(List<String> list)
	{
		int r = 0;

		for (QuestChapter chapter : treeGui.questFile.chapters)
		{
			for (Quest quest : chapter.quests)
			{
				if (quest.isComplete(treeGui.questFile.self))
				{
					for (QuestReward reward : quest.rewards)
					{
						if (!treeGui.questFile.isRewardClaimed(reward))
						{
							r++;
						}
					}
				}
			}
		}

		list.add(I18n.format("ftbquests.gui.unclaimed_rewards") + ": " + TextFormatting.GOLD + r);
	}

	@Override
	public void onClicked(MouseButton button)
	{
		GuiHelper.playClickSound();

		if (ClientQuestFile.existsWithTeam())
		{
			new MessageClaimAllRewards().sendToServer();
		}
	}
}