import java.awt.AWTPermission;
import java.io.FilePermission;
import java.net.SocketPermission;
import java.security.Permission;
import java.util.PropertyPermission;
import java.util.logging.Level;

import nl.moj.model.Tester;
import nl.moj.security.AbstractSecurityDelegate;


public class AllowScriptingSecurityDelegate extends AbstractSecurityDelegate implements Tester.SecurityDelegate {

    public AllowScriptingSecurityDelegate() {
        super();
    }
    
    protected void setAllowedClasses() {        
    }
    protected void setIllegalClasses() {        
    }

    public void checkPermission(Permission perm, Object context) throws SecurityException {
        boolean fail=false;
        //
        if (perm instanceof RuntimePermission) {
            //
            // Cannot exit VM.
            //
            if ("exitVM".equals(perm.getName())) fail=true;
            //
            // Cannot change security manager (duh!)
            //
            if ("setSecurityManager".equals(perm.getName())) fail=true;
            //
            // Cannot create a new ClassLoader
            //
            //if ("createClassLoader".equals(perm.getName())) {
            //    
            //    fail=true;
            //}
            //
        } else if (perm instanceof FilePermission) {
            //
            // Cannot access moj files out of workspace
            //
            if (perm.getActions().equals("read")) {
                // Cannot read any java files.
                if (perm.getName().toLowerCase().endsWith(".java")) fail=true;
                // Can only read class files that are in a workspace/*/bin dir.
                if (perm.getName().toLowerCase().endsWith(".class")) {
                    if (perm.getName().indexOf("workspace")<0) fail=true;
                    if (perm.getName().indexOf("bin")<0) fail=true;
                }
            } else {
                //
                // Cannot write,create or delete files anywhere.
                //
                fail=true;
                //
            }
            //
        } else if (perm instanceof SocketPermission) {
            //
            // No Socket Communication.
            //
            fail=true;
            //
        } else if (perm instanceof PropertyPermission) {
            //
            // Cannot change properties.
            //
            if (!perm.getActions().equals("read")) fail=true;
            //
        } else if (perm instanceof AWTPermission) {
            //
            // No accessing the event queue.
            //
            if ("accessEventQueue".equals(perm.getName())) fail=true;
            //
        }
        //
        //
        //
        if (fail) {
            log.log(Level.SEVERE,Thread.currentThread().getName()+" : Permission Denied : "+perm);
            throw new SecurityException("Permission Denied : "+perm);
        }
        //
    }   
    
}