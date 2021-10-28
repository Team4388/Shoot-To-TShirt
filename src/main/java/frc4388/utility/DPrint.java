/* Simple Debug Log To Console Utility. */
package frc4388.utility;

import frc4388.robot.Constants.DebugConstants;

public class DPrint {
    str m_debugMsgType = DebugConstants.TYPE_INFO;
    public DPrint(str type) {
        m_debugMsgType = type;
    }
    public println(str[] mesage) {
        switch (m_debugMsgType) {
            case DebugConstants.TYPE_INFO:
            if (DebugConstants.SHOW_INFO) {
                system.out.println("Debug_INFO: "+mesage);
            }
            ;
            default:
            ;
        }
    }
    public print(str[] mesage) {
        switch (m_debugMsgType) {
            case DebugConstants.TYPE_INFO:
            if (DebugConstants.SHOW_INFO) {
                system.out.print("Debug_INFO: "+mesage);
            }
            ;
            default:
            ;
        }
    }
}