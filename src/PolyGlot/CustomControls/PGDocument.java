/*
 * Copyright (c) 2015, draque
 * All rights reserved.
 *
 * Licensed under: Creative Commons Attribution-NonCommercial 4.0 International Public License
 *  See LICENSE.TXT included with this code to read the full license agreement.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package PolyGlot.CustomControls;

import java.awt.Font;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

/**
 *
 * @author draque
 * 
 * This allows custom fonts which are unregistered in the local computer to be used
 * in abstract documents
 */
public class PGDocument extends DefaultStyledDocument {
    private final Font customFont;
    
    public PGDocument(Font _customFont) {
        customFont = _customFont;
    }
    
    /**
     * Gets font. Returns custom font if PolyGlot special entry found in attr
     * @param attr attributes to pull font from
     * @return font in attributes if no PGT entry, custom font otherwise
     */
    @Override
    public Font getFont(AttributeSet attr) {
        Font ret = super.getFont(attr);
        
        if (customFont != null 
                && StyleConstants.getFontFamily(attr).equals(customFont.getFamily())) {
            
            ret = customFont.deriveFont((float)StyleConstants.getFontSize(attr));            
        }
        
        return ret;
    }
    
    public void testInsert(int start) {
        LeafElement a = (LeafElement)this.createLeafElement(getDefaultRootElement(), null, BUFFER_SIZE_DEFAULT, BUFFER_SIZE_DEFAULT);
        try {            
            a.getDocument().insertString(start, "<img src=\"" + "https://www.topatoco.com/images/three-five.png" + "\">", null);
        } catch (BadLocationException e) {
            // do nothing
            // IOHandler.writeErrorLog(e);
        }
    }
}

