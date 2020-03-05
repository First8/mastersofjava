import java.security.Permission;


public class SecurityDelegateImpl implements nl.moj.model.Tester.SecurityDelegate {

	@Override
	public void checkPermission(Permission perm, Object context)
			throws SecurityException {
		
		
	}

	@Override
	public void checkClassLoading(String className) throws SecurityException {
		// TODO Auto-generated method stub
		
	}

}
