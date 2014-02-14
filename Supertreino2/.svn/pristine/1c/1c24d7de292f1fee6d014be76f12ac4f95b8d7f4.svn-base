package br.ufba.matc89.util;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
 
public abstract class Mascara {
    public static String removerCaracteres(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }
 
    public static TextWatcher aplicarMascara(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isAlterado;
            String old = "";
 
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Mascara.removerCaracteres(s.toString());
                String mascara = "";
                if (isAlterado) {
                    old = str;
                    isAlterado = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isAlterado = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
 
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }
 
            public void afterTextChanged(Editable s) {
            }
        };
    }
}