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
        String html = "<table width=\"100%\" border=\"1\" style=\"text-align:left; font-size: 10px; border:1px solid black;border-collapse: collapse;color:black;\">\n" +
"                      	<tbody>\n" +
"                      <tr>\n" +
"                          <td colspan=\"7\" style=\"font-size:10px;font-weight:bold;text-align: left;padding: 2px;white-space:nowrap;\">\n" +
"                              13561 - ADEILDO LOURENÇO DA SILVA\n" +
"                          </td>\n" +
"                      </tr>\n" +
"                      <tr>\n" +
"                          <td style=\"padding:5px;\">15693</td>\n" +
"                          <td style=\"padding:5px;\">14/02/2022 á 15/12/2022</td>\n" +
"                          <td style=\"padding:5px;\">CONTRATO TEMPORÁRIO</td>\n" +
"                          <td style=\"padding:5px;\">DOCENTE DO ENSINO FUNDAMENTAL</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">16</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                      </tr>\n" +
"                      <tr>\n" +
"                          <td style=\"padding:5px;\">15693</td>\n" +
"                          <td style=\"padding:5px;\">14/02/2022 á 15/12/2022</td>\n" +
"                          <td style=\"padding:5px;\">CONTRATO TEMPORÁRIO</td>\n" +
"                          <td style=\"padding:5px;\">DOCENTE DO ENSINO FUNDAMENTAL</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">18</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                      </tr>\n" +
"                      <tr>\n" +
"                          <td colspan=\"7\" style=\"font-size:10px;font-weight:bold;text-align: left;padding: 2px;white-space:nowrap;\">\n" +
"                              13545 - ADILEUZA SANTOS FREITAS\n" +
"                          </td>\n" +
"                      </tr>\n" +
"                      <tr>\n" +
"                          <td style=\"padding:5px;\">15869</td>\n" +
"                          <td style=\"padding:5px;\">14/02/2022 á 14/04/2022</td>\n" +
"                          <td style=\"padding:5px;\">CONTRATO TEMPORÁRIO</td>\n" +
"                          <td style=\"padding:5px;\">DOCENTE DO ENSINO FUNDAMENTAL</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">3</td>\n" +
"                          <td style=\"text-align:center;padding:5px;\">0</td>\n" +
"                      </tr>\n" +
"                      \n" +
"                      	</tbody>\n" +
"                      </table>";
        html = html + html + html;
        
        CoplanConvert.isEsconderCabRod = false;
        CoplanConvert.isEsconderCab = false;        
        CoplanConvert.isPaisagem = false;
        CoplanConvert.isMarginInformada=false;

        CoplanConvert.isCabecalhoHtml=true;
        CoplanConvert.htmlCabecalho = "<table width=\"100%\" border=\"1\" style=\"font-size: 10px;color:black;border:1px solid black;border-collapse: collapse;\">\n" + "    <tbody>\n" + "            <tr>\n" + "                <td colspan=\"2\" style=\"border-right:none;\">\n" + "                    <img src=\"https://wf.coplan.inf.br/educacao/PublicTempStorage/brasao/AY9HQ2KDUUY5UJCC4XZ80P97AWBLBNNJYYO9JDWP/1_unidade_gestora_brasao.jpg\" alt=\"Brasao\" width=\"40\"></img>\n" + "                </td>\n" + "                <td colspan=\"5\" style=\"padding: 4px;border-left:none;font-size:14px;font-weight:bold;\">\n" + "                    Prefeitura Municipal de MATO GROSSO <br></br>\n" + "                    Secretaria Municipal de Educa\u00e7\u00e3o\n" + "                </td>\n" + "            </tr>\n" + "        <tr>\n" + "            <td colspan=\"7\" style=\"padding:5px;background: rgb(214,214,214) !important;font-size:12px;font-weight:bold;text-align:center;\">\n" + "                RELA\u00c7\u00c3O DE ATRIBUI\u00c7\u00c3O: 07/02/2022 \u00e1 20/12/2022\n" + "            </td>\n" + "        </tr>\n" + "        <tr>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">Matr\u00edcula</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">Per\u00edodo</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">Tipo de Contrato</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">Fun\u00e7\u00e3o</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">CH ADM</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">CH AULAS</td>\n" + "            <td style=\"padding: 4px;white-space:nowrap;background: rgb(214,214,214) !important;font-weight:bold;font-size:12px;text-align:center;\">CH HTP</td>\n" + "        </tr>\n" + "    </tbody>\n" + "</table>\n";
        
        CoplanConvert.isRodapeHtml=true;
        CoplanConvert.htmlRodape = "<table width=\"100%\" style=\"text-align:left; font-size: 10px;color:black;border:none;\">\n" +
"<tbody>\n" +
"    <tr>\n" +
"        <td colspan=\"7\">\n" +
"            <hr width=\"100%\" style=\"border-top: 1px solid black;\" ></hr>\n" +
"        </td>\n" +
"    </tr>\n" +
"    <tr>\n" +
"        <td colspan=\"4\" style=\"white-space: nowrap;text-align: left;\">\n" +
"            <strong>Usuário:</strong> COPLAN - AELSON MARTINS\n" +
"        </td>\n" +
"        <td colspan=\"3\" style=\"    white-space: nowrap;text-align: right;\">\n" +
"            <strong>Data de Emissão:</strong> 10/02/2023\n" +
"        </td>\n" +
"    </tr>\n" +
"</tbody>\n" +
"</table>";
        CoplanConvert.Stringtopdf(html, caminhoPdf);
        
        System.out.println("Clique em qualquer coisa para finalizar");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name);
    }
}
