package br.lry.functions;

import com.borland.silktest.jtf.BrowserBaseState;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.common.BrowserType;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomTextField;
import br.lry.functions.*;
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
	public static boolean autLogin(Desktop agent, String user, String password) {
		try {
			
			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: INICIANDO LOGIN : APLICACAO VA");
			
			agent.<DomTextField>find("VA.Login.Usuario").setText(user);
			agent.<DomTextField>find("VA.Login.Senha").setText(password);
			agent.<DomButton>find("VA.Login.Avancar").click();

			AUT_CURRENT_LOG_OBJECT.logMensagem("AUT INFO: LOGIN REALIZADO COM SUCESSO");
			return true;
		} catch (java.lang.Exception e) {

			AUT_CURRENT_LOG_OBJECT.logMensagem(AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA,
					"AUT ERROR: LOGIN : APLICACAO VA");

			return false;
		}
	}
}
