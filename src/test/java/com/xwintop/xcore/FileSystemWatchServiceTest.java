package com.xwintop.xcore;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.nio.file.StandardWatchEventKinds;

import org.junit.Test;

/** 
 * @ClassName: FileSystemWatchServiceTest 
 * @Description: 系统文件监测
 * @author: xufeng
 * @date: 2017年7月27日 下午2:59:38  
 */
public class FileSystemWatchServiceTest {
	@Test
	public void fileSystemWatchServiceTest() {
		// define a folder root
		Path myDir = Paths.get("D:/TestXf/receive");
		try {
			WatchService watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey watckKey = watcher.take();
			List<WatchEvent<?>> events = watckKey.pollEvents();
			for (@SuppressWarnings("rawtypes") WatchEvent event : events) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println("Created: " + event.context().toString());
				}
				if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
					System.out.println("Delete: " + event.context().toString());
				}
				if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
					System.out.println("Modify: " + event.context().toString());
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	// WatchService 是线程安全的，跟踪文件事件的服务，一般是用独立线程启动跟踪
	@Test
	public void watchRNDir() throws IOException, InterruptedException {
		final Path path = Paths.get("D:/TestXf/receive");
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			// 给path路径加上文件观察服务
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			// start an infinite loop
			while (true) {
				// retrieve and remove the next watch key
				final WatchKey key = watchService.take();
				// get list of pending events for the watch key
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					// get the kind of event (create, modify, delete)
					final Kind<?> kind = watchEvent.kind();
					// handle OVERFLOW event
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}
					// 创建事件
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {

					}
					// 修改事件
					if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {

					}
					// 删除事件
					if (kind == StandardWatchEventKinds.ENTRY_DELETE) {

					}
					// get the filename for the event
					@SuppressWarnings("unchecked")
					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
					final Path filename = watchEventPath.context();
					// print it out
					System.out.println(kind + " -> " + filename);
				}
				// reset the keyf
				boolean valid = key.reset();
				// exit loop if the key is not valid (if the directory was
				// deleted, for
				if (!valid) {
					break;
				}
			}
		}
	}
}
