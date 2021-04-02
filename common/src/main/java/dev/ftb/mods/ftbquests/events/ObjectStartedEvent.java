package dev.ftb.mods.ftbquests.events;

import dev.ftb.mods.ftbquests.quest.Chapter;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.QuestFile;
import dev.ftb.mods.ftbquests.quest.QuestObject;
import dev.ftb.mods.ftbquests.quest.TeamData;
import dev.ftb.mods.ftbquests.quest.task.Task;
import me.shedaniel.architectury.event.Actor;
import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;
import net.minecraft.server.level.ServerPlayer;

import java.util.Date;
import java.util.List;

/**
 * @author LatvianModder
 */
public class ObjectStartedEvent<T extends QuestObject> {
	public static final Event<Actor<ObjectStartedEvent<?>>> GENERIC = EventFactory.createActorLoop();
	public static final Event<Actor<FileEvent>> FILE = EventFactory.createActorLoop();
	public static final Event<Actor<ChapterEvent>> CHAPTER = EventFactory.createActorLoop();
	public static final Event<Actor<QuestEvent>> QUEST = EventFactory.createActorLoop();
	public static final Event<Actor<TaskEvent>> TASK = EventFactory.createActorLoop();

	static {
		FILE.register(event -> GENERIC.invoker().act(event));
		CHAPTER.register(event -> GENERIC.invoker().act(event));
		QUEST.register(event -> GENERIC.invoker().act(event));
		TASK.register(event -> GENERIC.invoker().act(event));
	}

	private final QuestProgressEventData<T> data;

	private ObjectStartedEvent(QuestProgressEventData<T> d) {
		data = d;
	}

	public boolean isCancelable() {
		return true;
	}

	public Date getTime() {
		return data.time;
	}

	public TeamData getData() {
		return data.teamData;
	}

	public T getObject() {
		return data.object;
	}

	public List<ServerPlayer> getOnlineMembers() {
		return data.onlineMembers;
	}

	public List<ServerPlayer> getNotifiedPlayers() {
		return data.notifiedPlayers;
	}

	@Deprecated
	public TeamData getTeam() {
		return getData();
	}

	public static class FileEvent extends ObjectStartedEvent<QuestFile> {
		public FileEvent(QuestProgressEventData<QuestFile> d) {
			super(d);
		}

		public QuestFile getFile() {
			return getObject();
		}
	}

	public static class ChapterEvent extends ObjectStartedEvent<Chapter> {
		public ChapterEvent(QuestProgressEventData<Chapter> d) {
			super(d);
		}

		public Chapter getChapter() {
			return getObject();
		}
	}

	public static class QuestEvent extends ObjectStartedEvent<Quest> {
		public QuestEvent(QuestProgressEventData<Quest> d) {
			super(d);
		}

		public Quest getQuest() {
			return getObject();
		}
	}

	public static class TaskEvent extends ObjectStartedEvent<Task> {
		public TaskEvent(QuestProgressEventData<Task> d) {
			super(d);
		}

		public Task getTask() {
			return getObject();
		}
	}
}