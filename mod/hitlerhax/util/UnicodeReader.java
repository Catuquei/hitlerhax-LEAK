// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class UnicodeReader extends Reader
{
    private static final int BOM_SIZE = 4;
    private final InputStreamReader reader;
    
    public UnicodeReader(final InputStream in, final String defaultEncoding) throws IOException {
        final byte[] bom = new byte[4];
        final PushbackInputStream pushbackStream = new PushbackInputStream(in, 4);
        final int n = pushbackStream.read(bom, 0, bom.length);
        String encoding;
        int unread;
        if (bom[0] == -17 && bom[1] == -69 && bom[2] == -65) {
            encoding = "UTF-8";
            unread = n - 3;
        }
        else if (bom[0] == -2 && bom[1] == -1) {
            encoding = "UTF-16BE";
            unread = n - 2;
        }
        else if (bom[0] == -1 && bom[1] == -2) {
            encoding = "UTF-16LE";
            unread = n - 2;
        }
        else if (bom[0] == 0 && bom[1] == 0 && bom[2] == -2 && bom[3] == -1) {
            encoding = "UTF-32BE";
            unread = n - 4;
        }
        else if (bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0) {
            encoding = "UTF-32LE";
            unread = n - 4;
        }
        else {
            encoding = defaultEncoding;
            unread = n;
        }
        if (unread > 0) {
            pushbackStream.unread(bom, n - unread, unread);
        }
        else if (unread < -1) {
            pushbackStream.unread(bom, 0, 0);
        }
        if (encoding == null) {
            this.reader = new InputStreamReader(pushbackStream);
        }
        else {
            this.reader = new InputStreamReader(pushbackStream, encoding);
        }
    }
    
    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return this.reader.read(cbuf, off, len);
    }
    
    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
