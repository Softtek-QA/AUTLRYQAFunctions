package br.lry.functions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.util.Stack;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import org.junit.Test;

public class AUTProjectsFunctions {
	/**
	 * Op��es de de configura��es poss�veis para o c�lculo de modulos
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_OPTION_CALC_MODULOS {
	/**
	 * 
	 * REPRESENTA VALORES EM QUE O RESTO GERADO PELO CALCULO DO M�DULO � MAIOR QUE
	 * ZERO
	 * 
	 */
	RETORNAR_VALORES_MAIORES_QUE_ZERO,
	/**
	 * 
	 * REPRESENTA VALORES EM QUE O RESTO GERADO PELO CALCULO DO M�DULO � (0)
	 * 
	 */
	RETORNAR_VALORES_MODULO_ZERO,
	/**
	 * 
	 * REPRESENTA VALORES EM QUE O RESTO GERADO PELO CALCULO DO M�DULO � (1)
	 * 
	 */
	RETORNAR_VALORES_MODULO_UM,
	/**
	 * 
	 * REPRESENTA VALORES EM QUE O RESTO GERADO PELO CALCULO DO M�DULO EST� ENTRE
	 * LIMITES DEFINIDOS (MINIMO E MAXIMO DO RESTO)
	 * 
	 */
	RETORNAR_VALORES_MODULO_DEF_PARAMETROS
	}

	public static class AUTNumerosRandomicos extends java.util.Random {

		/**
		 * 
		 * Lista de objetos selecion�vei
		 * 
		 */
		private Object[] itensOut = null;

		public AUTNumerosRandomicos() {
			super();
			// TODO Auto-generated constructor stub
		}

		public AUTNumerosRandomicos(long arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

		public Object selecionarProximoItem() {
			Integer indexItem = this.nextInt(itensOut.length);
			return itensOut[indexItem];
		}

		public AUTNumerosRandomicos(Object[] itens) {
			itensOut = itens;
			java.util.Arrays.sort(itensOut);
		}
	}

	/**
	 * Classe respons�vel pelo gerenciamento de logs dos sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTLogMensagem {
		/**
		 * 
		 * Objeto arquivo (IO) - Log de Mensagens
		 * 
		 */
		private java.io.File fileLogDinamic;
		java.io.FileOutputStream fileLogPadrao;
		java.io.BufferedOutputStream buffOutputLogPadrao;
		Integer numeroMaxLinhasLog = 5;

		/**
		 * 
		 * Caminho do arquivo de dados associado ao gerenciador de logs
		 * 
		 */
		private String caminhoArquivoDinamico = "logs/%s.txt";
		private String caminhoArquivoPadrao = "AUTLOG001.txt";
		/**
		 * 
		 * Vari�vel de configura��o do modo de opera��o do log(visual, arquivos)
		 * 
		 */
		private boolean exibirMensagemConsole = true;

		public static enum AUT_TIPO_MSG_LOG {
			/**
			 * 
			 * MENSAGEM INFORMATIVA PARA O USUARIO
			 * 
			 */
			MENSAGEM_INFORMATIVA,
			/**
			 * 
			 * MENSAGEM DE ALERTA PARA O USUARIO
			 * 
			 */
			MENSAGEM_ALERTA_USUARIO,
			/**
			 * 
			 * MENSAGEM PARA INFORMAR UM ERRO TESTE
			 * 
			 */
			MENSAGEM_ERRO_TESTE,

			/**
			 * 
			 * 
			 * MENSAGEM PARA INFORMAR UM ERRO NO SISTEMA
			 * 
			 */
			MENSAGEM_ERRO_SISTEMA;

			@Override
			public String toString() {
				switch (this) {
				case MENSAGEM_ALERTA_USUARIO: {

					return "ATENCAO USUARIO";
				}
				case MENSAGEM_ERRO_SISTEMA: {

					return "ERRO NO SISTEMA";
				}
				case MENSAGEM_ERRO_TESTE: {

					return "ERRO NO CASO DE TESTES: FALHOU";
				}
				case MENSAGEM_INFORMATIVA: {

					return "INFORMACAO";
				}
				}

				return "";
			}
		}

		/**
		 * 
		 * Habilita a exibi��o de mensagens no console de sa�da padr�o do sistema
		 * 
		 */
		public void habilitarExibicaoLogMsg() {
			exibirMensagemConsole = true;
		}

		/**
		 * 
		 * Desabilita a exibi��o de mensagens no log de sa�da padr�o do sistema
		 * 
		 * 
		 */
		public void desabilitarExibicaoLogMsg() {
			exibirMensagemConsole = false;
		}

		/**
		 * 
		 * Verifica o status de configura��o do log de mensagens padr�o do sistema
		 * 
		 * @return boolean - Retorna true (exibi��o de mensagens no console habilitada),
		 *         caso contr�rio false
		 * 
		 */
		public boolean exibicaoLogHabilitada() {
			//return exibirMensagemConsole;
			return true;
		}

		/**
		 * 
		 * Registra mensagem no arquivo de log e direciona para o console de sa�da
		 * padr�o
		 * 
		 * @param tipoMensagem - Tipo da mensagem
		 * @param mensagem     - Mensagem que ser� registrada no log do sistema
		 * 
		 */
		public void logMensagem(AUT_TIPO_MSG_LOG tipoMensagem, Object mensagem) {
			String formatMsg = "";

			switch (tipoMensagem) {
			case MENSAGEM_ALERTA_USUARIO: {

				formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(), mensagem.toString());

				if (exibicaoLogHabilitada()) {
					System.out.println(formatMsg);
				} else {
					// registrarLog(formatMsg);
				}

				break;
			}
			case MENSAGEM_ERRO_SISTEMA: {

				formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(), mensagem.toString());

				if (exibicaoLogHabilitada()) {
					System.out.println(formatMsg);
				} else {
					// registrarLog(formatMsg);
				}

				break;
			}
			case MENSAGEM_ERRO_TESTE: {

				formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(), mensagem.toString());

				if (exibicaoLogHabilitada()) {
					System.out.println(formatMsg);
				} else {
					// registrarLog(formatMsg);
				}

				break;
			}
			case MENSAGEM_INFORMATIVA: {

				formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(), mensagem.toString());

				if (exibicaoLogHabilitada()) {
					System.out.println(formatMsg);
				} else {
					// registrarLog(formatMsg);
				}

				break;
			}
			}
		}

		/**
		 * 
		 * Registra log de mensagem no formato padr�o
		 * 
		 * 
		 * Tipo padr�o : AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA
		 * 
		 * @param mensagem - Mensagem de envio para log
		 * 
		 */
		public void logMensagem(Object mensagem) {
			logMensagem(AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA, mensagem);
		}

		public void registrarLog(String mensagem) {
			try {
				buffOutputLogPadrao.write(mensagem.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * 
		 * Configura��es de inicializa��o da classe
		 * 
		 */
		public void configInit() {
			try {

				System.out.println("AUT INFO : CRIANDO ARQUIVO DE LOGS DO SISTEMA");

				fileLogPadrao = new java.io.FileOutputStream(caminhoArquivoPadrao);

				buffOutputLogPadrao = new java.io.BufferedOutputStream(fileLogPadrao);

			} catch (FileNotFoundException e) {

				System.out.println("AUT INIT DATA ERROR: INICIALIZACAO DO ARQUIVO DE LOG");

				System.out.println(e.getMessage());

				e.printStackTrace();
			}

			System.out.println("AUT INFO : CONFIGURANDO VARIAVEIS AMBIENTE");

			/******************** CONFIGURACAO DE VARI�VEIS AMBIENTE **********************/

		}

		/**
		 * Construtor padr�o da classe
		 */
		public AUTLogMensagem() {
			configInit();
		}
	}

	/**
	 * Classe respons�vel pelo desenvolvimento de express�es regulares para teste e
	 * formata��o de dados
	 * 
	 * @author Softtek - QA
	 *
	 */
	/**
	 * Define os tipos de sa�da de mercadoria poss�veis
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_FLUXO_SAIDA {

		/**
		 * 
		 * SAIDA DE CAIXA
		 * 
		 */
		CAIXA,

		/**
		 * 
		 * RETIRADA NA LOJA
		 * 
		 */
		RETIRADA_INTERNA,

		/**
		 * 
		 * ENTREGA A DOMICILIO
		 * 
		 */
		ENTREGA,

		/**
		 * 
		 * RETIDA NA LOJA EXTERNA AGENDADA
		 * 
		 */
		RETIRADA_EXTERNA_AGENDADA,
		/**
		 * 
		 * RETIRADA NA LOJA INTERNA NO MOMENTO DA COMPRA
		 */
		RETIRADA_INTERNA_IMEDIATA,
		/**
		 * 
		 * RETIRADA EXTERNA NO MOMENTO DA COMPRA
		 */
		RETIRADA_EXTERNA_IMEDIATA,

		/**
		 * 
		 * ENTREGA EXPRESSA
		 * 
		 */
		RETIRADA_INTERNA_AGENDADA, ENTREGA_ECONOMICA;

		@Override
		public String toString() {
			switch (this) {
			case CAIXA: {
				return "CAIXA";
			}
			case ENTREGA: {
				return "ENTREGA";
			}
			case ENTREGA_ECONOMICA: {
				return "ENTREGA ECONOMICA";
			}
			case RETIRADA_EXTERNA_IMEDIATA: {
				return "2_RETIRA_EXTERNA_IMEDIATA";
			}
			case RETIRADA_EXTERNA_AGENDADA: {
				return "3_RETIRA_EXTERNA_AGENDADA";
			}
			case RETIRADA_INTERNA_IMEDIATA: {
				return "4_RETIRA_INTERNA_IMEDIATA";
			}
			case RETIRADA_INTERNA_AGENDADA: {
				return "5_RETIRA_INTERNA_AGENDADA";
			}

			}

			return "";
		}
	}

	/**
	 * 
	 * Rela��o de lojas e Centrais de distribui��o cadastradas no sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_LOJA {
		LJ_OU_CD_0001, LJ_OU_CD_0002, LJ_OU_CD_0003, LJ_OU_CD_0004, LJ_OU_CD_0005, LJ_OU_CD_0007, LJ_OU_CD_0008,
		LJ_OU_CD_0009, LJ_OU_CD_0010, LJ_OU_CD_0011, LJ_OU_CD_0012, LJ_OU_CD_0013, LJ_OU_CD_0014, LJ_OU_CD_0015,
		LJ_OU_CD_0016, LJ_OU_CD_0017, LJ_OU_CD_0018, LJ_OU_CD_0019, LJ_OU_CD_0020, LJ_OU_CD_0021, LJ_OU_CD_0022,
		LJ_OU_CD_0023, LJ_OU_CD_0024, LJ_OU_CD_0025, LJ_OU_CD_0026, LJ_OU_CD_0027, LJ_OU_CD_0028, LJ_OU_CD_0029,
		LJ_OU_CD_0030, LJ_OU_CD_0031, LJ_OU_CD_0032, LJ_OU_CD_0033, LJ_OU_CD_0034, LJ_OU_CD_0035, LJ_OU_CD_0036,
		LJ_OU_CD_0037, LJ_OU_CD_0038, LJ_OU_CD_0039, LJ_OU_CD_0040, LJ_OU_CD_0041, LJ_OU_CD_0042, LJ_OU_CD_0043,
		LJ_OU_CD_0044, LJ_OU_CD_0045, LJ_OU_CD_0046, LJ_OU_CD_0047, LJ_OU_CD_0048, LJ_OU_CD_0049, LJ_OU_CD_0050,
		LJ_OU_CD_0051, LJ_OU_CD_0052, LJ_OU_CD_0053, LJ_OU_CD_0055, LJ_OU_CD_0056, LJ_OU_CD_0057, LJ_OU_CD_0058,
		LJ_OU_CD_0201, LJ_OU_CD_0519, LJ_OU_CD_0802, LJ_OU_CD_0999, LJ_OU_CD_CD01, LJ_OU_CD_LOJ1, LJ_OU_CD_XD01,
		LJ_OU_CD_ZAC1, LJ_OU_CD_ZAL1, LJ_OU_CD_ZAM1, LJ_OU_CD_ZAP1, LJ_OU_CD_ZBA1, LJ_OU_CD_ZCE1, LJ_OU_CD_ZDF1,
		LJ_OU_CD_ZES1, LJ_OU_CD_ZGO1, LJ_OU_CD_ZMA1, LJ_OU_CD_ZMG1, LJ_OU_CD_ZMS1, LJ_OU_CD_ZMT1, LJ_OU_CD_ZPA1,
		LJ_OU_CD_ZPB1, LJ_OU_CD_ZPE1, LJ_OU_CD_ZPI1, LJ_OU_CD_ZPR1, LJ_OU_CD_ZRJ1, LJ_OU_CD_ZRN1, LJ_OU_CD_ZRO1,
		LJ_OU_CD_ZRR1, LJ_OU_CD_ZRS1, LJ_OU_CD_ZSC1, LJ_OU_CD_ZSE1, LJ_OU_CD_ZSP1, LJ_OU_CD_ZTO1;

		@Override
		public String toString() {

			switch (this) {
			case LJ_OU_CD_0001: {
				return "0001";
			}
			case LJ_OU_CD_0002: {
				return "0002";
			}
			case LJ_OU_CD_0003: {
				return "0003";
			}
			case LJ_OU_CD_0004: {
				return "0004";
			}
			case LJ_OU_CD_0005: {
				return "0005";
			}
			case LJ_OU_CD_0007: {
				return "0007";
			}
			case LJ_OU_CD_0008: {
				return "0008";
			}
			case LJ_OU_CD_0009: {
				return "0009";
			}
			case LJ_OU_CD_0010: {
				return "0010";
			}
			case LJ_OU_CD_0011: {
				return "0011";
			}
			case LJ_OU_CD_0012: {
				return "0012";
			}
			case LJ_OU_CD_0013: {
				return "0013";
			}
			case LJ_OU_CD_0014: {
				return "0014";
			}
			case LJ_OU_CD_0015: {
				return "0015";
			}
			case LJ_OU_CD_0016: {
				return "0016";
			}
			case LJ_OU_CD_0017: {
				return "0017";
			}
			case LJ_OU_CD_0018: {
				return "0018";
			}
			case LJ_OU_CD_0019: {
				return "0019";
			}
			case LJ_OU_CD_0020: {
				return "0020";
			}
			case LJ_OU_CD_0021: {
				return "0021";
			}
			case LJ_OU_CD_0022: {
				return "0022";
			}
			case LJ_OU_CD_0023: {
				return "0023";
			}
			case LJ_OU_CD_0024: {
				return "0024";
			}
			case LJ_OU_CD_0025: {
				return "0025";
			}
			case LJ_OU_CD_0026: {
				return "0026";
			}
			case LJ_OU_CD_0027: {
				return "0027";
			}
			case LJ_OU_CD_0028: {
				return "0028";
			}
			case LJ_OU_CD_0029: {
				return "0029";
			}
			case LJ_OU_CD_0030: {
				return "0030";
			}
			case LJ_OU_CD_0031: {
				return "0031";
			}
			case LJ_OU_CD_0032: {
				return "0032";
			}
			case LJ_OU_CD_0033: {
				return "0033";
			}
			case LJ_OU_CD_0034: {
				return "0034";
			}
			case LJ_OU_CD_0035: {
				return "0035";
			}
			case LJ_OU_CD_0036: {
				return "0036";
			}
			case LJ_OU_CD_0037: {
				return "0037";
			}
			case LJ_OU_CD_0038: {
				return "0038";
			}
			case LJ_OU_CD_0039: {
				return "0039";
			}
			case LJ_OU_CD_0040: {
				return "0040";
			}
			case LJ_OU_CD_0041: {
				return "0041";
			}
			case LJ_OU_CD_0042: {
				return "0042";
			}
			case LJ_OU_CD_0043: {
				return "0043";
			}
			case LJ_OU_CD_0044: {
				return "0044";
			}
			case LJ_OU_CD_0045: {
				return "0045";
			}
			case LJ_OU_CD_0046: {
				return "0046";
			}
			case LJ_OU_CD_0047: {
				return "0047";
			}
			case LJ_OU_CD_0048: {
				return "0048";
			}
			case LJ_OU_CD_0049: {
				return "0049";
			}
			case LJ_OU_CD_0050: {
				return "0050";
			}
			case LJ_OU_CD_0051: {
				return "0051";
			}
			case LJ_OU_CD_0052: {
				return "0052";
			}
			case LJ_OU_CD_0053: {
				return "0053";
			}
			case LJ_OU_CD_0055: {
				return "0055";
			}
			case LJ_OU_CD_0056: {
				return "0056";
			}
			case LJ_OU_CD_0057: {
				return "0057";
			}
			case LJ_OU_CD_0058: {
				return "0058";
			}
			case LJ_OU_CD_0201: {
				return "0201";
			}
			case LJ_OU_CD_0519: {
				return "0519";
			}
			case LJ_OU_CD_0802: {
				return "0802";
			}
			case LJ_OU_CD_0999: {
				return "0999";
			}
			case LJ_OU_CD_CD01: {
				return "CD01";
			}
			case LJ_OU_CD_LOJ1: {
				return "LOJ1";
			}
			case LJ_OU_CD_XD01: {
				return "XD01";
			}
			case LJ_OU_CD_ZAC1: {
				return "ZAC1";
			}
			case LJ_OU_CD_ZAL1: {
				return "ZAL1";
			}
			case LJ_OU_CD_ZAM1: {
				return "ZAM1";
			}
			case LJ_OU_CD_ZAP1: {
				return "ZAP1";
			}
			case LJ_OU_CD_ZBA1: {
				return "ZBA1";
			}
			case LJ_OU_CD_ZCE1: {
				return "ZCE1";
			}
			case LJ_OU_CD_ZDF1: {
				return "ZDF1";
			}
			case LJ_OU_CD_ZES1: {
				return "ZES1";
			}
			case LJ_OU_CD_ZGO1: {
				return "ZGO1";
			}
			case LJ_OU_CD_ZMA1: {
				return "ZMA1";
			}
			case LJ_OU_CD_ZMG1: {
				return "ZMG1";
			}
			case LJ_OU_CD_ZMS1: {
				return "ZMS1";
			}
			case LJ_OU_CD_ZMT1: {
				return "ZMT1";
			}
			case LJ_OU_CD_ZPA1: {
				return "ZPA1";
			}
			case LJ_OU_CD_ZPB1: {
				return "ZPB1";
			}
			case LJ_OU_CD_ZPE1: {
				return "ZPE1";
			}
			case LJ_OU_CD_ZPI1: {
				return "ZPI1";
			}
			case LJ_OU_CD_ZPR1: {
				return "ZPR1";
			}
			case LJ_OU_CD_ZRJ1: {
				return "ZRJ1";
			}
			case LJ_OU_CD_ZRN1: {
				return "ZRN1";
			}
			case LJ_OU_CD_ZRO1: {
				return "ZRO1";
			}
			case LJ_OU_CD_ZRR1: {
				return "ZRR1";
			}
			case LJ_OU_CD_ZRS1: {
				return "ZRS1";
			}
			case LJ_OU_CD_ZSC1: {
				return "ZSC1";
			}
			case LJ_OU_CD_ZSE1: {
				return "ZSE1";
			}
			case LJ_OU_CD_ZSP1: {
				return "ZSP1";
			}
			case LJ_OU_CD_ZTO1: {
				return "ZTO1";
			}
			}

			return "";
		}

	}

	/**
	 * 
	 * Tipos de dep�sitos cadastrados no sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_DEPOSITO {
		DEPOSITO_C010, DEPOSITO_C050, DEPOSITO_C060, DEPOSITO_C070, DEPOSITO_C080, DEPOSITO_C081, DEPOSITO_C082,
		DEPOSITO_C090, DEPOSITO_C100, DEPOSITO_C101, DEPOSITO_C102, DEPOSITO_C103, DEPOSITO_C200, DEPOSITO_C500,
		DEPOSITO_C020, DEPOSITO_C030, DEPOSITO_C040;

		@Override
		public String toString() {
			switch (this) {
			case DEPOSITO_C010: {
				return "C010";
			}
			case DEPOSITO_C050: {
				return "C050";
			}
			case DEPOSITO_C060: {
				return "C060";
			}
			case DEPOSITO_C070: {
				return "C070";
			}
			case DEPOSITO_C080: {
				return "C080";
			}
			case DEPOSITO_C081: {
				return "C081";
			}
			case DEPOSITO_C082: {
				return "C082";
			}
			case DEPOSITO_C090: {
				return "C090";
			}
			case DEPOSITO_C100: {
				return "C100";
			}
			case DEPOSITO_C101: {
				return "C101";
			}
			case DEPOSITO_C102: {
				return "C102";
			}
			case DEPOSITO_C103: {
				return "C103";
			}
			case DEPOSITO_C200: {
				return "C200";
			}
			case DEPOSITO_C500: {
				return "C500";
			}
			case DEPOSITO_C020: {
				return "C020";
			}
			case DEPOSITO_C030: {
				return "C030";
			}
			case DEPOSITO_C040: {
				return "C040";
			}
			}

			return "";
		}

	}

	/**
	 * 
	 * Retorna um conjunto de poss�veis valores
	 * 
	 * @param caracteres - Caractere separador de colunas
	 * 
	 * @return java.util.HashMap<Object,Object> - Parametros de sa�da
	 * 
	 */
	public static java.util.HashMap<Integer, String> autSplitParameters(String expressaoRegularParaDivisaoColunas,
			String conteudoAnalise) {
		java.util.HashMap<Integer, String> prmOut = new java.util.HashMap<Integer, String>();
		Integer indexRow = 0;
		java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularParaDivisaoColunas);
		java.util.regex.Matcher analise = padrao.matcher(conteudoAnalise);

		System.out.println("AUT LOADER PARAMS: INIT");

		while (analise.find()) {

			prmOut.put(indexRow, analise.group());

			System.out.println(analise.group());
			indexRow++;
		}

		System.out.println("AUT LOADER PARAMS: END");

		return prmOut;
	}

	/**
	 * 
	 * Seleciona um tipo de fluxo de caixa aleatoriamente
	 * 
	 * @param fluxos - Tipo de fluxo de saida de caixa
	 * 
	 * @return AUT_TIPO_FLUXO_SAIDA - Objeto que define o fluxo de caixa
	 * 
	 */
	public static AUT_TIPO_FLUXO_SAIDA selecionarTipoFluxoSaidaRND(AUT_TIPO_FLUXO_SAIDA[] fluxos) {
		AUTNumerosRandomicos rndItens = new AUTNumerosRandomicos(fluxos);

		return (AUT_TIPO_FLUXO_SAIDA) rndItens.selecionarProximoItem();
	}

	public static Object[] calcModulosPossiveis(AUT_OPTION_CALC_MODULOS tipoCalc, Integer divisorModulo,
			Integer limiteMinimoRestoModulo, Integer limiteMaximoRestoModulo, Integer numeroBaseCalculo) {
		java.util.List<Integer> listaNum = new java.util.ArrayList<Integer>();
		Object[] itensOut = null;
		java.util.Stack stkOut = new Stack();

		Integer valorMod = 0;
		for (int i = numeroBaseCalculo; i >= 1; i--) {

			switch (tipoCalc) {
			case RETORNAR_VALORES_MAIORES_QUE_ZERO: {

				break;
			}
			case RETORNAR_VALORES_MODULO_UM: {

				break;
			}
			case RETORNAR_VALORES_MODULO_ZERO: {

				break;
			}
			case RETORNAR_VALORES_MODULO_DEF_PARAMETROS: {
				valorMod = (divisorModulo > 0 ? (i % divisorModulo) : -1);

				if (valorMod >= limiteMinimoRestoModulo && valorMod <= limiteMaximoRestoModulo
						&& !valorMod.equals(-1)) {
					// System.out.println(String.format("VALOR BASE: %s VALOR REDUZIDO:
					// %s",numeroBaseCalculo,i));
					// System.out.println(String.format("MODULO POSSIVEL: %s", valorMod));
					stkOut.push(i);

				}
				break;
			}
			}

		}

		itensOut = stkOut.toArray();

		return itensOut;
	}

	public static Integer calcNovoValorBaseReduzidoModDiv(Object[] valoresPossiveis, Integer valorDigito,
			Integer valorBaseAtual) {
		Integer valorSelect = 0, indexCorrente = 0, valorProcurado = 0;
		System.out.println();
		System.out.println("VALOR MEDIO SELECIONADO PARA CALCULO: ");

		Integer indexMid = java.lang.Math.round((valoresPossiveis.length / 2));
		valorSelect = Integer.parseInt(valoresPossiveis[indexMid].toString());
		System.out.println(valorSelect);

		for (int i = indexMid; i >= 0; i--) {
			indexCorrente = i;
			valorProcurado = (Integer) valoresPossiveis[indexCorrente];
			if ((valorSelect - valorProcurado) == valorDigito) {
				System.out.println("AUT NOVO VALOR BASE DEFINIDO:");
				System.out.println(valorProcurado);
				break;
			}
		}

		return 0;
	}

	public static Integer gerarFuncPorDig(Integer indexDigito, java.util.List<Object> listaDigitos,
			java.util.List<Object> listaSomaProduto) {
		Integer contDig = 1;
		String pontoFuncao = "X";
		String funcaoOut = "";
		Integer digOut = -1;
		for (Object dig : listaDigitos) {
			if (contDig.equals(indexDigito)) {
				funcaoOut += pontoFuncao;
			} else {
				funcaoOut += dig;
			}
			contDig++;
		}

		if (indexDigito.equals(10)) {
			Integer resultCalc = 0;
			for (Object prod : listaSomaProduto) {
				resultCalc += (Integer) prod;
			}

			Integer somaProds = resultCalc;
			Integer digCalc1 = (somaProds % 11);
			Integer dig1 = (digCalc1 == 0 || digCalc1 == 1 ? 0 : (11 - digCalc1));

			digOut = dig1;

			funcaoOut += dig1.toString();
		} else if (indexDigito.equals(11)) {
			Integer resultCalc = 0;
			for (Object prod : listaSomaProduto) {
				resultCalc += (Integer) prod;
			}
			Integer somaProds = resultCalc;
			Integer digCalc2 = (somaProds % 11);
			Integer dig2 = (digCalc2 == 0 || digCalc2 == 1 ? 0 : (11 - digCalc2));

			digOut = dig2;
			funcaoOut += dig2.toString();
		}

		return digOut;
	}

	public static String gerarCPF() {

		// Itens para valida��o do digito 1
		Integer[] digs1 = new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		// Itens para valida��o do digito 2
		Integer[] digs2 = new Integer[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		// Digitos cpf
		Integer[] digsSelect = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// Chaves de Fun��o
		Object[] configChavesAtribuicaoBaseRND = new Object[] { "X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8", "X9" };
		// Fun��es para atribui��o de digitos
		AUTNumerosRandomicos rnNumber = new AUTNumerosRandomicos(digsSelect);
		java.util.List<Object> digsBase = new java.util.ArrayList<Object>();
		java.util.List<Object> digsSomaProd = new java.util.ArrayList<Object>();
		Integer somaProdDigts = 0;

		for (Object dig : digs1) {
			Integer x = (Integer) rnNumber.selecionarProximoItem();
			Integer digCpf = (Integer) dig;
			digsBase.add(x);
			digsSomaProd.add((x * digCpf));
		}

		Integer dig1 = (Integer) gerarFuncPorDig(10, digsBase, digsSomaProd);
		digsBase.add(dig1);
		digsSomaProd.add(dig1 * 2);
		Integer contDig = 0;

		somaProdDigts = 0;
		digsSomaProd.clear();

		for (Object dig : digs2) {
			Integer x = (Integer) digsBase.get(contDig);
			Integer digCpf = (Integer) dig;

			digsSomaProd.add(x * Integer.parseInt(dig.toString()));

			contDig++;
		}

		digsBase.add(gerarFuncPorDig(11, digsBase, digsSomaProd));
		java.lang.StringBuffer strCpf = new java.lang.StringBuffer();

		for (Object dig : digsBase) {
			strCpf.append(dig);
		}

		System.out.println(String.format("CPF GERADO : %s", strCpf.toString()));

		return strCpf.toString();
	}

	public static String gerarCNPJ() {

		// Itens para valida��o do digito 1
		Integer[] digs1 = new Integer[] { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		// Itens para valida��o do digito 2
		Integer[] digs2 = new Integer[] { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		// Digitos CNPJ
		Integer[] digsSelect = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// Chaves de Fun��o
		Object[] configChavesAtribuicaoBaseRND = new Object[] { "X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8", "X9" };
		// Fun��es para atribui��o de digitos
		AUTNumerosRandomicos rnNumber = new AUTNumerosRandomicos(digsSelect);
		java.util.List<Object> digsBase = new java.util.ArrayList<Object>();
		java.util.List<Object> digsSomaProd = new java.util.ArrayList<Object>();
		Integer somaProdDigts = 0;

		for (Object dig : digs1) {
			Integer x = (Integer) rnNumber.selecionarProximoItem();
			Integer digCNPJ = (Integer) dig;
			digsBase.add(x);
			digsSomaProd.add((x * digCNPJ));
		}

		Integer dig1 = (Integer) gerarFuncPorDig(11, digsBase, digsSomaProd);
		digsBase.add(dig1);
		digsSomaProd.add(dig1 * 2);
		Integer contDig = 0;

		somaProdDigts = 0;
		digsSomaProd.clear();

		for (Object dig : digs2) {
			Integer x = (Integer) digsBase.get(contDig);
			Integer digCNPJ = (Integer) dig;

			digsSomaProd.add(x * Integer.parseInt(dig.toString()));

			contDig++;
		}

		digsBase.add(gerarFuncPorDig(11, digsBase, digsSomaProd));
		java.lang.StringBuffer strCNPJ = new java.lang.StringBuffer();

		for (Object dig : digsBase) {
			strCNPJ.append(dig);
		}

		System.out.println(String.format("CNPJ GERADO : %s", strCNPJ.toString()));

		return strCNPJ.toString();
	}

	public static String gerarEstrangeiro() {

		// Digitos Estrangeiro
		Integer[] dig = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		java.lang.StringBuilder vari = new java.lang.StringBuilder();
		AUTNumerosRandomicos randonNum = new AUTNumerosRandomicos(dig);

		// Gera numeros aleatorios
		for (int i = 0; i < 9; i++) {

			Integer x = (Integer) randonNum.selecionarProximoItem();
			vari.append(x);
		}
		System.out.println(String.format("Estrangeiro : %s", vari.toString()));

		return vari.toString();
	}

	public static String gerarItemChaveRandomico(Integer totalCaracteres) {

		// Digitos Estrangeiro
		Integer[] dig = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		java.lang.StringBuilder vari = new java.lang.StringBuilder();
		AUTNumerosRandomicos randonNum = new AUTNumerosRandomicos(dig);

		// Gera numeros aleatorios
		for (int i = 0; i < totalCaracteres; i++) {

			Integer x = (Integer) randonNum.selecionarProximoItem();
			vari.append(x);
		}
		System.out.println(String.format("Estrangeiro : %s", vari.toString()));

		return vari.toString();
	}
	
	
	/**
	 * Carrega os registros do Hash de dados onde a chave e o valor correspondem as expressões regulares
	 * 
	 * @param regExpKey - Expressão regular para chave do hash
	 * @param regExpValue - Expressao regular para o valor relacionado a chave corrente
	 * @param parametros - Hash de dados para pesquisa
	 * 
	 * @return Hash - registros que correspondem as expressões regulares
	 */
	public java.util.HashMap<String,String> autFindItemOnHash(String regExpKey,String regExpValue,java.util.HashMap<String,String> parametros){
		java.util.HashMap<String,String> paramsOut = new java.util.HashMap<String,String>();
		java.util.regex.Pattern padraoKey = java.util.regex.Pattern.compile(regExpKey);
		java.util.regex.Pattern padraoValue = java.util.regex.Pattern.compile(regExpValue);
		for(String key : parametros.keySet()) {
			java.util.regex.Matcher analise = padraoKey.matcher(key);
			if(analise.find()) {
				analise = padraoValue.matcher(parametros.get(key));
				if(analise.find()) {
					paramsOut.put(key, parametros.get(key));
				}
			}
		}

		return paramsOut;
	}
}