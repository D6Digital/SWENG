	import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

    public class videoTutorial {

        public static void main(String[] args) {
        	NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC"
                );
        	
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        }
    }