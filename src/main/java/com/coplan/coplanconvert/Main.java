package com.coplan.coplanconvert;

import com.itextpdf.text.DocumentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author paulo.benedito
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws com.itextpdf.text.DocumentException
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void main(String[] args) throws DocumentException, NoSuchAlgorithmException, Exception {
        String caminhoPdf = "c:\\temp\\pdftestenovo.pdf";
        String caminhoPdfWatermark = "c:\\temp\\pdfteste_watermark.pdf";
        
        Boolean isPaisagem = true;
        Boolean isEsconderCabRod = false;
        String html = "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\">\n" +
"			<p style=\"text-align:center\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">ESTADO DO MATO GROSSO</span></span><br />\n" +
"			<span style=\"font-family:arial,helvetica,sans-serif; font-size:12px\">PREFEITURA MUNICIPAL DE ALTO ARAGUAIA</span><br />\n" +
"			<span style=\"font-family:arial,helvetica,sans-serif; font-size:12px\">03.579.836/0001- 80</span><br />\n" +
"			<span style=\"font-family:arial,helvetica,sans-serif; font-size:12px\">RUA SILVIO JOSÉ DE CASTRO MAIA, SILVIO MAIA, N. 513,CENTRO - ALTO ARAGUAIA/MT</span><br />\n" +
"			<span style=\"font-family:arial,helvetica,sans-serif; font-size:12px\">ESTADO DO MATO GROSSO</span><br />\n" +
"			&nbsp;</p>\n" +
"			</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td style=\"background-color:rgb(204, 204, 204); text-align:center; vertical-align:middle\">\n" +
"			<p><br />\n" +
"			<span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\">CDA - CERTIDÃO DE INSCRIÇÃO EM DÍVIDA ATIVA</span><br />\n" +
"			<span style=\"font-family:arial,helvetica,sans-serif\">Número:&nbsp;#CDA_NUM_ANO#</span></span><br />\n" +
"			&nbsp;</p>\n" +
"			</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td style=\"background-color:rgb(204, 204, 204); text-align:center; vertical-align:middle\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>1 - Contribuinte e Origem do Débito</strong></span></span></td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td colspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Pessoa/Devedor:&nbsp; &nbsp; #CONT_NOME#</span></span></td>\n" +
"			<td colspan=\"2\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">CPF/CNPJ:&nbsp; &nbsp;<strong> </strong>#CONT_CPF_CNPJ#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Responsável:&nbsp; &nbsp; &nbsp;&nbsp;</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Nome Fantasia:&nbsp; &nbsp; &nbsp; #CONT_FANTASIA#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Pedido&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Quadra/Lote:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #IMOVEL_END_QUADRA#&nbsp;/&nbsp;#IMOVEL_END_LOTE#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">End. Imóvel&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #IMOVEL_ENDERECO#</span></span></td>\n" +
"			<td colspan=\"2\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Bairro:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;#IMOVEL_END_BAIRRO#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Endereço Pessoa:&nbsp; &nbsp; #CONT_END#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Bairro Pessoa:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #CONT_BAI#</span></span></td>\n" +
"			<td colspan=\"1\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">CEP:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #CONT_CEP#</span></span></td>\n" +
"			<td colspan=\"1\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Cidade:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #CONT_CID#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Origem:&nbsp; </span></span><span style=\"font-size:10px\">#TRIBUTO_CLASSIFICACAO_CDA#</span><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"font-size:10px\">&nbsp; </span>&nbsp; &nbsp; &nbsp;&nbsp;</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Natureza:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;TRIBUTÁRIA</span></span></td>\n" +
"			<td colspan=\"2\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Anos em Dívida:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; #COMP_DEB_SEM_QUEBRA#</span></span></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"3\" rowspan=\"1\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">Dados Adicionais:&nbsp; &nbsp;&nbsp;</span></span></td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<p><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Certificamos, estar o débito constante deste documento regularmente inscrito em dívida ativa para fins de cobrança judicial nos termos do art. 2 &sect; 5 da Lei 6.830, de 22/09/80.</span></span></p>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<table style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td style=\"background-color:rgb(204, 204, 204); text-align:center; vertical-align:middle\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>2 - Inscrição da Dívida / Descrição do Débito</strong></span></span></td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<p>#TRIBUTO_IMOVEL#</p>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:730px\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Valor Original:</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>#VALOR#</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Juros:</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>#JUROS#</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Multa:</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>#MULTA#</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Correção:</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>#CORRECAO#</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Valor Total:</strong></span></span></td>\n" +
"			<td><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>#TOTAL#</strong></span></span></td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<p style=\"text-align:justify\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></span></p>\n" +
"\n" +
"<table style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td style=\"background-color:rgb(204, 204, 204); text-align:center; vertical-align:middle\"><span style=\"font-size:12px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>3 - Origem / Natureza / Fundamento Legal</strong></span></span></td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<p style=\"text-align:justify\"><span style=\"font-size:12px\">&nbsp;Certificamos na forma da Lei 1.337/2001 - Código Tributário Municipal e suas alterações, que, em nome do sujeito passivo acima&nbsp; &nbsp;identificado, conta débitos junto a esta Entidade.&nbsp;</span></p>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<p style=\"margin-left:5,0000pt\"><span style=\"font-family:arial,helvetica,sans-serif; font-size:12px\">Após o vencimento, o crédito estará sujeito aos juros de mora de 1% (um por cento) ao mês, incidente sobre o valor do tributo originário e multa equivalente 2% (dois por cento), incidente sobre o valor do tributo originário (art. 43, I e III da Lei 1.337/01), acrescido da taxa de expediente R$ 10,06 (art. 130, da Lei 1.337/2001).</span></p>\n" +
"\n" +
"<p style=\"text-align:justify\">&nbsp;</p>\n" +
"\n" +
"<p style=\"text-align:justify\">&nbsp;</p>\n" +
"\n" +
"<p style=\"text-align:justify\">&nbsp;</p>\n" +
"\n" +
"<p style=\"text-align:right\"><span style=\"font-size:10px\"><span style=\"font-family:arial,helvetica,sans-serif\">Alto Araguaia (MT), #DATA#</span></span></p>\n" +
"\n" +
"<p style=\"text-align:right\">&nbsp;</p>\n" +
"\n" +
"<p style=\"text-align:right\">&nbsp;</p>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100%\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"		</tr>\n" +
"		<tr>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\" style=\"text-align:center\"><img alt=\"\" src=\"http://www.gp.srv.br/tributario_altoaraguaia/arquivos/altoaraguaia/tributario/usuario/assinatura/14-ass.png\" style=\"height:73px; width:100px\" /></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td colspan=\"1\" rowspan=\"1\" style=\"text-align:center\">\n" +
"			<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:730px\">\n" +
"				<tbody>\n" +
"					<tr>\n" +
"						<td colspan=\"1\" rowspan=\"1\" style=\"text-align:center\"><span style=\"font-size:10px\"><span style=\"font-family:arial,helvetica,sans-serif\">____________________________________</span></span></td>\n" +
"					</tr>\n" +
"					<tr>\n" +
"					</tr>\n" +
"					<tr>\n" +
"					</tr>\n" +
"					<tr>\n" +
"						<td colspan=\"1\" rowspan=\"1\" style=\"text-align:center\"><span style=\"font-size:10px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>WELTON VILELA CARDOSO</strong></span></span></td>\n" +
"					</tr>\n" +
"					<tr>\n" +
"						<td colspan=\"1\" rowspan=\"1\" style=\"text-align:center\"><span style=\"font-size:10px\"><span style=\"font-family:arial,helvetica,sans-serif\"><strong>Secretário Municipal de Finanças</strong></span></span></td>\n" +
"					</tr>\n" +
"				</tbody>\n" +
"			</table>\n" +
"			</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>\n" +
"\n" +
"<p>&nbsp;</p>\n" +
"\n" +
"<p>&nbsp;</p>";
        html = html + html + html;
        
        CoplanConvert.isEsconderCabRod = false;
        CoplanConvert.isEsconderCab = true;
        CoplanConvert.Stringtopdf(html, caminhoPdf);
        
        System.out.println("Clique em qualquer coisa para finalizar");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name);
    }
}
