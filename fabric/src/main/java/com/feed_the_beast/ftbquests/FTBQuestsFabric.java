package com.feed_the_beast.ftbquests;

import com.feed_the_beast.ftbquests.FTBQuests;
import net.fabricmc.api.ModInitializer;

public class FTBQuestsFabric implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		new FTBQuests();
	}
}
