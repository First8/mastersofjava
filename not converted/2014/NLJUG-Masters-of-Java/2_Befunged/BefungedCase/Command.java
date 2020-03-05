/**
 * The command class represents a single command in a Befunge program. The class has only a single method:
 * {@link #execute(ExecutionContext)}.
 *
 * @author Jaap Coomans
 */
public abstract class Command {
	public abstract void execute(ExecutionContext context);

	/**
	 * Factory method that creates a new Command based on the given command character and the String mode of the PC.
	 *
	 * @param rawCommand
	 *            The raw command character
	 * @param stringMode
	 *            Is the PC in String mode?
	 * @return A new Command instance representing the raw command.
	 * @throws IllegalArgumentException
	 *             When the rawCommand is unknown.
	 */
	public static Command createCommand(char rawCommand, boolean stringMode) {
		if (rawCommand == '"') {
			return new StringModeCommand();
		} else if (stringMode) {
			return new PushValue(rawCommand);
		} else {
			switch (rawCommand) {
			case '@':
				return new Terminate();
			case ' ':
				return new BlankCommand();
			case '<':
				return new ChangeDirection(Direction.LEFT);
			case '>':
				return new ChangeDirection(Direction.RIGHT);
			case '^':
				return new ChangeDirection(Direction.UP);
			case 'v':
				return new ChangeDirection(Direction.DOWN);
			case '_':
				return new HorizontalIf();
			case '|':
				return new VerticalIf();
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				return new PushValue(rawCommand - 48);
			case '.':
				return new WriteNumber();
			case ',':
				return new WriteString();
			case ':':
				return new Duplicate();
			case '#':
				return new Bridge();
			case '+':
				return new Addition();
			case '-':
				return new Subtraction();
			case '*':
				return new Multiplication();
			case '/':
				return new Division();
			case '%':
				return new Modulo();
			case '`':
				return new GreaterThan();
			case '!':
				return new Not();
			default:
				throw new IllegalArgumentException("Unrecognized command '" + rawCommand + "'");
			}
		}
	}

	private static class Terminate extends Command {
		@Override
		public void execute(ExecutionContext context) {
			context.terminate();
		}
	}

	private static class BlankCommand extends Command {
		@Override
		public void execute(ExecutionContext context) {
			// Do nothing
		}
	}

	private static class ChangeDirection extends Command {
		private Direction direction;

		private ChangeDirection(Direction direction) {
			this.direction = direction;
		}

		@Override
		public void execute(ExecutionContext context) {
			context.getProgramCounter().setDirection(this.direction);
		}
	}

	private static class PushValue extends Command {
		private int value;

		private PushValue(int value) {
			this.value = value;
		}

		@Override
		public void execute(ExecutionContext context) {
			context.getStack().push(this.value);
		}
	}

	private static class StringModeCommand extends Command {
		@Override
		public void execute(ExecutionContext context) {
			context.getProgramCounter().toggleStringMode();
		}
	}

	private static class Duplicate extends Command {
		@Override
		public void execute(ExecutionContext context) {
			int value = 0;
			if (!context.getStack().isEmpty()) {
				value = context.getStack().peek();
			}

			context.getStack().push(value);
		}
	}

	private static class Bridge extends Command {
		@Override
		public void execute(ExecutionContext context) {
			context.getProgramCounter().move();
		}
	}

	private static abstract class PopStackCommand extends Command {
		@Override
		public final void execute(ExecutionContext context) {
			int value = 0;
			if (!context.getStack().isEmpty()) {
				value = context.getStack().pop();
			}

			this.processValue(context, value);
		}

		protected abstract void processValue(ExecutionContext context, int value);
	}

	private static class HorizontalIf extends PopStackCommand {
		@Override
		protected void processValue(ExecutionContext context, int value) {
			if (value == 0) {
				context.getProgramCounter().setDirection(Direction.RIGHT);
			} else {
				context.getProgramCounter().setDirection(Direction.LEFT);
			}
		}
	}

	private static class VerticalIf extends PopStackCommand {
		@Override
		protected void processValue(ExecutionContext context, int value) {
			if (value == 0) {
				context.getProgramCounter().setDirection(Direction.DOWN);
			} else {
				context.getProgramCounter().setDirection(Direction.UP);
			}
		}
	}

	private static class WriteString extends PopStackCommand {
		@Override
		protected void processValue(ExecutionContext context, int value) {
			context.writeOutput((char) value);
		}
	}

	private static class WriteNumber extends PopStackCommand {
		@Override
		protected void processValue(ExecutionContext context, int value) {
			context.writeOutput(Integer.toString(value));
		}
	}

	private static abstract class UnaryOperator extends Command {
		@Override
		public final void execute(ExecutionContext context) {
			int value = 0;
			if (!context.getStack().isEmpty()) {
				context.getStack().pop();
			}

			context.getStack().push(this.calculate(value));
		}

		protected abstract int calculate(int value);
	}

	private static class Not extends UnaryOperator {
		@Override
		protected int calculate(int value) {
			return value == 0 ? 1 : 0;
		}
	}

	private static abstract class BinaryOperator extends Command {
		@Override
		public final void execute(ExecutionContext context) {
			int a = 0;
			if (!context.getStack().isEmpty()) {
				a = context.getStack().pop();
			}

			int b = 0;
			if (!context.getStack().isEmpty()) {
				b = context.getStack().pop();
			}

			context.getStack().push(this.calculate(a, b));
		}

		protected abstract int calculate(int a, int b);
	}

	private static class Addition extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return a + b;
		}
	}

	private static class Subtraction extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return b - a;
		}
	}

	private static class Multiplication extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return a * b;
		}
	}

	private static class Division extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return b / a;
		}
	}

	private static class Modulo extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return b % a;
		}
	}

	private static class GreaterThan extends BinaryOperator {
		@Override
		protected int calculate(int a, int b) {
			return b > a ? 1 : 0;
		}
	}
}
