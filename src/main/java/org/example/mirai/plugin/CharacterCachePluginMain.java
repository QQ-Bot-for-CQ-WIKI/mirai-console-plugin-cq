package org.example.mirai.plugin;

import bean.CharacterHolder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import method.CharacterCaches;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 使用 Java 请把
 * {@code /src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin}
 * 文件内容改成 {@code org.example.mirai.plugin.JavaPluginMain} <br/>
 * 也就是当前主类全类名
 * <p>
 * 使用 Java 可以把 kotlin 源集删除且不会对项目有影响
 * <p>
 * 在 {@code settings.gradle.kts} 里改构建的插件名称、依赖库和插件版本
 * <p>
 * 在该示例下的 {@link JvmPluginDescription} 修改插件名称，id 和版本等
 * <p>
 * 可以使用 {@code src/test/kotlin/RunMirai.kt} 在 IDE 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */
@Slf4j
public final class CharacterCachePluginMain extends JavaPlugin {

    public static final CharacterCachePluginMain INSTANCE = new CharacterCachePluginMain();

    public static List<CharacterHolder> allCharacters;

    private CharacterCachePluginMain() {
        super(new JvmPluginDescriptionBuilder("org.example.mirai-cq-JAVA", "0.1.0")
                .info("EG")
                .build());
    }

    @Override
    public void onEnable() {
        log.info("Java CharacterCachesFromWeb load++++++++++++++++ 加载");
        CharacterCaches characterCaches = new CharacterCaches();
        characterCaches.cacheAllCharactersFromWeb();
        allCharacters = characterCaches.getAllCharacters();
        log.info("总共多少个英雄：{}",allCharacters.size());

//        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
//        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
//            //监听群消息
//            getLogger().info(g.getMessage().contentToString());
//
//        });
//        eventChannel.subscribeAlways(
//                FriendMessageEvent.class, f -> {
//                    //监听好友消息
//                    getLogger().info(f.getMessage().contentToString());
//                });
    }
}
