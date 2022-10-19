import java.io.IOException;
import java.nio.file.*;

public class WatchFolderExample {
    public static void watch(Path path){
        FileSystem fs = path.getFileSystem();
        try(WatchService service = fs.newWatchService()) {
            path.register(service, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true){
                try {
                    WatchKey key = service.take();
                    for (WatchEvent watchEvent : key.pollEvents()){
                        WatchEvent.Kind kind = watchEvent.kind();
                        if(StandardWatchEventKinds.ENTRY_MODIFY == kind){
                            Path newPath = ((WatchEvent<Path>) watchEvent).context();
                            System.out.println("New path created: "+newPath);
                        }
                    }
                    if(!key.reset()) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Temp");
        watch(path);
    }
}
