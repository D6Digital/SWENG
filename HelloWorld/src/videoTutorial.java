	import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

    public class videoTutorial {

        public static void main(String[] args) {
        	NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "vlc-2.0.1"
            );
        	
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        }
    }