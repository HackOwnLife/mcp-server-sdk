package cloud.fesh.msgplatform.utils;

import java.lang.management.ManagementFactory;
import java.util.List;

public class DebugUtils {

    private static Boolean DEBUG_FLAG;

    public static boolean isDebug() {
        if (DEBUG_FLAG == null) {
            DEBUG_FLAG = false;
            List<String> arguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
            for (String arg : arguments) {
                if (arg.contains("-agentlib:jdwp")) {
                    DEBUG_FLAG = true;
                    break;
                }
            }
        }
        return DEBUG_FLAG;
    }
}
