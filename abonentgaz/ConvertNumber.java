/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

/**
 * ����� ��� �������� ����� � �����.
 * ��������� �������� ������� ����� �� ������� � ���������� �����
 * @author lera
 */
public class ConvertNumber {
    private String numText;// ��������� ���������� ��������� �����
    private String value;// ��������� ������������� �����
    private Language lang;// ��� ����� �������� �����
    private String[][] langUnits;// ������ ��� �������� ��������� ������������ �����
    
    public ConvertNumber(Language lang){
        this.lang = lang;
        langUnits = new String[10][10];// ����� ������ �������
        selLang();
    }
    
    private void selLang(){
        // ��������� ������ � ����������� �� ���������� �����
        switch(lang){
            case LANGRUSS:
                setRussLang();
                break;
            case LANGUKR:
                setUkrLang();
                break;
        }
//        fullupArray();// ����������� ������
    }
    
    private void setRussLang(){
        String[][] ed = {
            {"0", "", "������", "", "", "", "�����", "�����", "���������", "���������"},
            {"1", "����", "�����������", "", "���", "����", "������", "�����", "�������", "���������"},
            {"2", "���", "����������", "��������", "������", "���", "������", "�����", "��������", "���������"},
            {"3", "���", "����������", "��������", "������", "���", "������", "�����", "��������", "���������"},
            {"4", "������", "������������", "�����", "���������", "������", "������", "�����", "��������", "���������"},
            {"5", "����", "����������", "���������", "�������", "����", "�����", "�����", "���������", "���������"},
            {"6", "�����", "�����������", "����������", "��������", "�����", "�����", "�����", "���������", "���������"},
            {"7", "����", "����������", "���������", "�������", "����", "�����", "�����", "���������", "���������"},
            {"8", "������", "������������", "�����������", "���������", "������", "�����", "�����", "���������", "���������"},
            {"9", "������", "������������", "���������", "���������", "������", "�����", "�����", "���������", "���������"}
        };
        langUnits = ed;
    }
    
    private void setUkrLang(){
        String[][] ed = {
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
            {"", "����", "���", "���", "������", 
            "�'���", "�����", "��", "���", "���'���"},
            {"������", "����������", "����������", "����������",
            "������������", "�'���������", "�����������", "���������",
            "����������", "���'���������"},
            {"", "", "��������", "��������", "�����", "�'��������",
            "���������", "�������", "��������", "���'������"},
            {"", "���", "����", "������", "���������", "�'������",
            "��������", "�����", "������", "���'�����"},
            {"", "����", "��", "���", "������", "�'���", "�����", "��",
            "���", "���'���"},
            {"�����", "������", "������", "������", "������", "�����", "�����",
            "�����", "�����", "�����"},
            {"�����", "�����", "�����", "�����", "�����", "�����", "�����",
            "�����", "�����", "�����"},
            {"������", "�����", "������", "������", "������",
            "������", "������", "������", "������", "������"},
            {"������", "������", "������", "������", "������",
            "������", "������", "������", "������", "������"}
        };
        langUnits = ed;
    }
    
    private String transNumber(){
        String retValue;
        int lenValue;// ����� ������
        // ��������� �������� ��������
        switch (value) {
            case "":
                retValue = "";
                break;
            case "0":
                retValue = "����";
                break;
            default:
                // ���������� ����� ������
                lenValue = value.length();
                //                System.out.println("lenValue=" + lenValue);
                // ��������� �����
                if(lenValue <= 3){
                    // �����
                    retValue = Hundreds(value, 1, 0);
                }else if(lenValue <= 6){
                    // ������
                    retValue = Hundreds(value.substring(0, lenValue - 3), 5, 1) +
                            " " + Hundreds(value.substring(lenValue - 3), 1, 0);
                } else if(lenValue <= 9){
                    // ��������
                    String retHundred = Hundreds(value.substring(lenValue - 3), 
                            1, 0);// �����
                    
                    String retMillion;
                    String retThousand;
                    /* ��������� �������� ��� �����: ���� �������� ������� ��
                    * ����� "000", ����� �������� ������� ��������, �����
                    * ���������� ���� ����
                    */
                    String subSTR = value.substring(lenValue - 6, lenValue - 3);
//                    System.out.println("subSTR = " + subSTR);
                    if(!subSTR.equals("000")){
                        retThousand = Hundreds(subSTR, 5, 1);
                        retMillion = retThousand + " " + retHundred;// ������
                    } else {
                        retMillion = retHundred;
                    }
                    retValue = Hundreds(value.substring(0, lenValue - 6), 1, 2) +
                            " " + retMillion;// ��������
                } else{
                    retValue = "";
                }
                break;
        }
        return retValue;
    }
    
    /**
     * ���������� ������� ����������
     * @param numCol - ����� �������
     * @param mArray - ������ � �������
     */
    private void fullupArray(){
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++)
                System.out.print(langUnits[i][j] + " ");
            System.out.println();
        }
            
    }
    
    /**
     * 
     * @param numString - ����������� ����� (������)
     * @param numCol - ����� ������� � ������� langUnits ��� ���������
     * @param numeric - ������������� (0 - �������, 1 - ������, 2 - ��������)
     * @return ���������� ��������� ���������� �����
     */
    private String Hundreds(String numString, int numCol, int numeric){
        String retval = null;
        int length = numString.length();
//        System.out.println(value);
//        System.out.println("numstring=" + numString + " " + "length=" + length);
        // � ����������� �� ����� ��������� ����� ��������
        switch(length){
            case 1:// ��� ����������� �����
                retval = oneUnits(numString, numCol, numeric);
                break;
            case 2:// ��� ���������� �����
                retval = twoUnits(numString, numCol, numeric);
                break;
            case 3:// ��� ���������� �����
                retval = threeUnits(numString, numCol, numeric);
                break;
        }
        return retval;
    }
    
    /**
     * @param numString - ����������� ����� (������)
     * @param numCol - ����� ������� � ������� langUnits
     * @param numeric - ������������� (0 - �������, 1 - ������, 2 - ��������)
     * @return ���������� ��������� ���������� ����� ��� ����������� �����
     */
    private String oneUnits(String numString, int numCol, int numeric){
        String retval = null;
        for(int i = 0; i < 10; i++){
//            System.out.println(langUnits[i][0]);
            if(numString.equals(langUnits[i][0])){
                switch(numeric){
                    case 0:
                        retval = langUnits[i][numCol];
                        break;
                    case 1:
                        retval = langUnits[i][numCol] + " " + 
                                langUnits[i][6];
                        break;
                    case 2:
                        retval = langUnits[i][numCol] + " " + 
                                langUnits[i][8];
                        break;
                }
                break;
            }
        }
        return retval;
    }
    
    
    /**
     * @param numString - ����������� ����� (������)
     * @param numCol - ����� ������� � ������� langUnits
     * @param numeric - ������������� (0 - �������, 1 - ������, 2 - ��������)
     * @return ���������� ��������� ���������� ����� ��� ���������� �����
     */
    private String twoUnits(String numString, int numCol, int numeric){
        String retval = null;
        int i, j;
        int length = numString.length();
        String subSTR = numString.substring(0, 1);
        
        // ����������, � ����� ����� ���������� �����
        if(subSTR.equals("1")){
            // ���������� � 1
            for(i = 0; i < 10; i ++ ){
                if(numString.substring(length - 
                        1).equals(langUnits[i][0])){
                    switch(numeric){
                        case 0:
                            retval = langUnits[i][2];
                            break;
                        case 1:
                            retval = langUnits[i][2] + " " + langUnits[i][7];
                            break;
                        case 2:
                            retval = langUnits[i][2] + " " + langUnits[i][9];
                            break;
                    }
                    break;
                }
            }
        } else{
            // ���������� � ������ �����
            for(i = 2; i < 10; i ++ ){
                if(numString.substring(0, 1).equals(langUnits[i][0])){
                    for(j = 0; j < 10; j++){
                        if(numString.substring(1, 2).equals(langUnits[j][0])){
                            switch(numeric){
                                case 0:
                                    retval = langUnits[i][3] + " " +
                                            langUnits[j][numCol];
                                    break;
                                case 1:
                                    retval = langUnits[i][3] + " " +
                                            langUnits[j][numCol] + " " +
                                            langUnits[j][6];
                                    break;
                                case 2:
                                    retval = langUnits[i][3] + " " +
                                            langUnits[j][numCol] + " " +
                                            langUnits[j][8];
                                    break;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return retval;
    }
    
    /**
     * @param numString - ����������� ����� (������)
     * @param numCol - ����� ������� � ������� langUnits
     * @param numeric - ������������� (0 - �������, 1 - ������, 2 - ��������)
     * @return ���������� ��������� ���������� ����� ��� ���������� �����
     */
    private String threeUnits(String numString, int numCol, int numeric){
        String retval = null;
        int i, j;
        String leftsubSTR = numString.substring(0, 1);
//        System.out.print(leftsubSTR +" ");
        String midsubSTR = numString.substring(1, 2);
//        System.out.print(midsubSTR + " ");
        String rightsubSTR = numString.substring(numString.length() - 1);
//        System.out.println(rightsubSTR);
        for(i = 0; i < 10; i++){
            if(leftsubSTR.equals(langUnits[i][0])){
                if(midsubSTR.equals("1")){
                    // ���������� �������
                    for(j = 0; j < 10; j++){
                        if(rightsubSTR.equals(langUnits[j][0])){
                            switch(numeric){
                                case 0:
                                    retval = !leftsubSTR.equals("0") ?
                                            langUnits[i][4] + " " + 
                                            langUnits[j][2] : langUnits[j][2];
                                    break;
                                case 1:
                                    retval = !leftsubSTR.equals("0") ?
                                            langUnits[i][4] + " " +
                                            langUnits[j][2] + " " +
                                            langUnits[j][7] : langUnits[j][2] + 
                                            " " + langUnits[j][7];
                                    break;
                                case 2:
                                    retval = langUnits[i][4] + " " +
                                            langUnits[j][2] + " " +
                                            langUnits[j][9];
                                    break;
                            }
                        }
                    }
                } else{
                    for(j = 0; j <10; j++){
                        if(midsubSTR.equals(langUnits[j][0])){
                            for(int l = 0; l <10; l++){
                                if(rightsubSTR.equals(langUnits[l][0])){
                                    // ���������� ������������ ����� ������
                                    switch(numeric){
                                        case 0:
                                            retval = !leftsubSTR.equals("0") ? 
                                                    langUnits[i][4] + " " +
                                                    langUnits[j][3] + " " +
                                                    langUnits[l][numCol] :
                                                    !midsubSTR.equals("0") ?
                                                    langUnits[j][3] + " " +
                                                    langUnits[l][numCol] :
                                                    langUnits[l][numCol];
                                            break;
                                        case 1:
                                            if(!langUnits[l][numCol].equals("")){
                                                retval = !leftsubSTR.equals("0") ? 
                                                        langUnits[i][4] + " " +
                                                        langUnits[j][3] + " " +
                                                        langUnits[l][numCol] + " " +
                                                        langUnits[l][6] : 
                                                        langUnits[j][3] + " " +
                                                        langUnits[l][numCol] + " " +
                                                        langUnits[l][6];
                                            } else {
                                                retval = !leftsubSTR.equals("0") ? 
                                                        langUnits[i][4] + " " +
                                                        langUnits[j][3] + " " +
                                                        langUnits[l][6] : 
                                                        langUnits[j][3] + " " +
                                                        langUnits[l][6];
                                            }
                                            break;
                                        case 2:
                                            retval = langUnits[i][4] + " " +
                                                    langUnits[j][3] + " " +
                                                    langUnits[l][numCol] + " " +
                                                    langUnits[l][8];
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return retval;
    }
    

    /**
     * @return the numText
     */
    public String getNumText() {
        return numText;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
        // �������� ������� ��������
        numText = transNumber();
    }

    /**
     * @return the lang
     */
    public Language getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(Language lang) {
        this.lang = lang;
    }
    
    public enum Language{
        LANGRUSS,
        LANGUKR;
    }
}
