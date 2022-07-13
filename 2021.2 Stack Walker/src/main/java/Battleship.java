import java.lang.StackWalker.StackFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;

public class Battleship {
	private final StackWalker stackWalker = StackWalker.getInstance();
	private String filter = "Battleship";

	public String getUnitCaller() {
		return stackWalker.walk(this::toViewUnitCaller);
	}
	private RisingStackWalker risingStackWalker = new RisingStackWalker();

	private String toViewUnitCaller(Stream<StackFrame> stackFrameStream) {
		String result = stackFrameStream
				.filter(frame -> !frame.getClassName().endsWith(filter))
				.findFirst()
				.map(frame -> frame.getClassName() + "#" + frame.getMethodName() + ", Line " + frame.getLineNumber())
				.orElse("Unknown caller");
		return result;
	}

	public boolean start() {
	    int code = risingStackWalker.getSecretCodeUsingStreams(15);
		boolean isStarted = risingStackWalker.isValidCode(code) && code > 0;
		if (isStarted) {
			System.out.println("---BATTLESHIP STARTED---");
			BattleshipGear battleshipGear = new BattleshipGear();
			battleshipGear.switchToWarpSpeed("4");
		} else {
			processWithoutAuthentication();
		}
		return isStarted;
	}

	private void yieldYediStyle(String message) {
		Random rnd = new Random();
		int nr = rnd.nextInt(quotes.length);
		System.out.println("---------------------------------------------");
		System.out.println("---" + quotes[nr] + "---");
		System.out.println("---------------------------------------------");
		System.out.println("### " + message);
	}

	public void processWithoutAuthentication() {
		List<StackFrame> stackTrace = stackWalker.walk(this::toList);
		yieldYediStyle("demo Stackwalker API");
		log(stackTrace);

		yieldYediStyle("demo Stackwalker API, internal methods only");

		stackTrace = stackWalker.walk(this::toListInternalMethods);

		log(stackTrace);

		String line = stackWalker.walk(this::toViewUnitCaller);
		yieldYediStyle("demo Stackwalker API, method calling this unit:" + line);

	}

	public List<StackFrame> toList(Stream<StackFrame> stackFrameStream) {
		return stackFrameStream.collect(Collectors.toList());
	}

	public List<StackFrame> toListInternalMethods(Stream<StackFrame> stackFrameStream) {
		return stackFrameStream.filter(frame -> frame.getClassName().contains(filter)).collect(Collectors.toList());
	}

	public void log(List<StackFrame> stackTrace) {
		int counter = 0;
		for (StackFrame stackFrame : stackTrace) {
			System.out.println(counter + "\t" + stackFrame.getClassName() + "#" + stackFrame.getMethodName() + ", Line "
					+ stackFrame.getLineNumber());
			counter++;
		}
	}

	private String[] quotes = new String[] {
			"'You are the Chosen One. You have brought balance to this world.' Yoda",
			"'Stay on this path, and you will do it again for the galaxy.' Yoda",
            "'The greatest teacher, failure is.' Yoda",
            "'Pass on what you have learned' Yoda",
            "'Named must be your fear before banish it you can.' Yoda",
			"'I am just a simple man trying to make my way in the universe.' Jango Fett",
			"'The Force will be with you. Always.' Obi-Wan Kenobi", "'Do. Or do not. There is no try.' Yoda" };
}
