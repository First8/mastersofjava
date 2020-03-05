/*
 * Commando set voor de virtuele CPU
 */
public enum VirtualCPU {
	LD {
		@Override
		public void exec(CPUState state, int destRegister, int value) {
			state.setRegister(destRegister, value);
		}
	},

	MV {
		@Override
		public void exec(CPUState state, int destRegister, int sourceRegister) {
			state.setRegister(destRegister, state.getRegister(sourceRegister));
		}
	},
	
	ADD {
		@Override
		public void exec(CPUState state, int destRegister, int sourceRegister) {
			state.setRegister(destRegister, (state.getRegister(sourceRegister) + state.getRegister(destRegister)) & 0xFF);
		}
	},
	
	OUT {
		@Override
		public void exec(CPUState state, int sourceRegister, int port) {
			state.setPort(port, state.getRegister(sourceRegister));
		}
	};
	
	public void exec(CPUState state, int oper1, int oper2) {
		// Implementeer de virtual CPU
	}
}
