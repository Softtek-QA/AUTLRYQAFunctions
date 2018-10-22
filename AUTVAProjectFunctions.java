package br.lry.functions;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.win32.AccessibleControl;
import com.borland.silktest.jtf.xbrowser.BrowserApplication;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.xbrowser.DomTextField;
import br.lry.functions.AUTProjectsFunctions.AUTLogMensagem;
import br.lry.functions.AUTProjectsFunctions.AUTLogMensagem.AUT_TIPO_MSG_LOG;

/**
 * 
 * Funções genericas - Projeto VA
 * 
 * 
 */
public abstract class AUTVAProjectFunctions {

	public static AUTLogMensagem AUT_CURRENT_LOG_OBJECT = new AUTLogMensagem();

	/**
	 * 
	 * Executa procedimentos de login na aplicação VA
	 * 
	 * @param agent    - Objeto de conexão
	 * @param user     - Usuário VA
	 * @param password - Senha VA
	 * 
	 * @return boolean - True caso o procedimento seja realizado com sucesso false
	 *         caso contrário
	 * 
	 */
	public static boolean autLoginBoitata(Desktop agent, String user, String password) {

		try {

			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: INICIANDO LOGIN : APLICACAO VA");
			if(agent.<BrowserApplication>find("VA02").exists("Mensagem",10000)) {
				agent.<AccessibleControl>find("VA02.Mensagem").click();
			}
			agent.<DomElement>find("VA.TelaLoginHomog1.EntrarMenuInicial").click();
			agent.<DomTextField>find("VA.TelaLoginHomog1.Usuario").click();
			System.out.println(user + "Usuário no textbox");
			agent.<DomTextField>find("VA.TelaLoginHomog1.Usuario").setText(user);
			agent.<DomTextField>find("VA.TelaLoginHomog1.Usuario").click();
			agent.<DomTextField>find("VA.TelaLoginHomog1.Senha").setText(password);
			agent.<DomButton>find("VA.TelaLoginHomog1.EntrarLogin").click();
			
			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: LOGIN REALIZADO COM SUCESSO");
			return true;
		} catch (java.lang.Exception e) {
			
			AUT_CURRENT_LOG_OBJECT.logMensagem(AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA,
					"AUT ERROR: LOGIN : APLICACAO VA");

			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	
	public static boolean autLoginVA(Desktop agent, String user, String password) {
		try {
			
			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: INICIANDO LOGIN : APLICACAO VA");
			
			agent.<DomTextField>find("VA.Login.Usuario").click();
			agent.<DomTextField>find("VA.Login.Usuario").setText(user);
			agent.<DomTextField>find("VA.Login.Senha").click();
			agent.<DomTextField>find("VA.Login.Senha").setText(password);
			agent.<DomButton>find("VA.Login.Avancar").click();
			
			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: LOGIN REALIZADO COM SUCESSO");
			return true;
		} catch (java.lang.Exception e) {
			
			AUT_CURRENT_LOG_OBJECT.logMensagem(AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA,
					"AUT ERROR: LOGIN : APLICACAO VA");

			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
}
