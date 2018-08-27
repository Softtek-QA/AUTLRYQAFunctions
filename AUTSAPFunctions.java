package br.lry.functions;

//********************  FUNÇÕES REUTILIZAVEIS : APLICACAÇÃO SAP***********************************


public class AUTSAPFunctions{
	
	public static com.borland.silktest.jtf.Desktop autStartSAPApplication() {
		
		com.borland.silktest.jtf.Desktop agent = new com.borland.silktest.jtf.Desktop();				
		com.borland.silktest.jtf.BaseState appConfig = new com.borland.silktest.jtf.BaseState();
		appConfig.setLocator("SAP");		
		
		agent.executeBaseState(appConfig);
		
		return agent;
	}


}