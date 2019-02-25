import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution implements ApplesOranges {

	@Override
	public int total(Path p) throws IOException {
		if (!p.toFile().exists()) {
			return 0;
		}
		final AtomicInteger atomicTotal = new AtomicInteger(0);

		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes b)
					throws IOException {
				System.out.println("walk file : "
						+ file.toFile().getCanonicalPath() + " - start: "
						+ atomicTotal.intValue());
				if (!file.toString().endsWith(".foo")) {
					return FileVisitResult.CONTINUE;
				}
				System.out.println("read length : "
						+ Files.lines(file).toArray().length);
				
				
				Files.lines(file)
						.filter(l -> l.startsWith("total:"))
						.forEach(
								l -> {
									String[] num = l.split(":");
									if (num.length > 1) {
										int newValue = atomicTotal.intValue()
												+ Integer.valueOf(num[1]);

						
										atomicTotal.getAndSet(newValue);
									}
								});
				System.out.println("file : "
						+ file + " - endval: "
						+ atomicTotal.intValue());
				return FileVisitResult.CONTINUE;
			}
		});
		return atomicTotal.intValue();
	}
	
	public static void main(String[] args) throws Throwable {
    	ApplesOrangesTester tester = new ApplesOrangesTester(); 
    	tester.setSolution(new Solution());
    	for (int index=0;index< tester.getTestCountIncludeHidden();index++) {
    		System.out.println(index + " - " + tester.performTest(index)); 
    	}
    }
}
