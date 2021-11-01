/* Simple Debug Log To Console Utility. */
package frc4388.utility;

import frc4388.robot.Constants.DebugConstants;

public class DPrint {
    String m_debugMsgType = DebugConstants.TYPE_INFO;
    public DPrint(String type) {
        m_debugMsgType = type;
    }
    public void println(String mesage) {
        switch (m_debugMsgType) {
            case DebugConstants.TYPE_INFO:
            if (DebugConstants.SHOW_INFO) {
                System.out.println("Debug_INFO: "+mesage);
            }
            ;
            default:
            ;
        }
    }
    public void print(String mesage) {
        switch (m_debugMsgType) {
            case DebugConstants.TYPE_INFO:
            if (DebugConstants.SHOW_INFO) {
                System.out.print("Debug_INFO: "+mesage);
            }
            ;
            default:
            ;
        }
    }
}