/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

/**
 * класс дл€ перевода чисел в слова.
 * позвол€ет выбирать перевод числа на русский и украинский €зыки
 * @author lera
 */
public class ConvertNumber {
    private String numText;// словесный эквивалент введЄнного числа
    private String value;// строковое представление числа
    private Language lang;// тип €зыка перевода числа
    private String[][] langUnits;// массив дл€ хранени€ словесных эквавалентов чисел
    
    public ConvertNumber(Language lang){
        this.lang = lang;
        langUnits = new String[10][10];// задаЄм размер массива
        selLang();
    }
    
    private void selLang(){
        // заполн€ем массив в зависимости от выбранного €зыка
        switch(lang){
            case LANGRUSS:
                setRussLang();
                break;
            case LANGUKR:
                setUkrLang();
                break;
        }
//        fullupArray();// распечатаем массив
    }
    
    private void setRussLang(){
        String[][] ed = {
            {"0", "", "дес€ть", "", "", "", "тыс€ч", "тыс€ч", "миллионов", "миллионов"},
            {"1", "один", "одиннадцать", "", "сто", "одна", "тыс€ча", "тыс€ч", "миллион", "миллионов"},
            {"2", "два", "двенадцать", "двадцать", "двести", "две", "тыс€чи", "тыс€ч", "миллиона", "миллионов"},
            {"3", "три", "тринадцать", "тридцать", "триста", "три", "тыс€чи", "тыс€ч", "миллиона", "миллионов"},
            {"4", "четыре", "четырнадцать", "сорок", "четыреста", "четыре", "тыс€чи", "тыс€ч", "миллиона", "миллионов"},
            {"5", "п€ть", "п€тнадцать", "п€тьдес€т", "п€тьсот", "п€ть", "тыс€ч", "тыс€ч", "миллионов", "миллионов"},
            {"6", "шесть", "шестнадцать", "шестьдес€т", "шестьсот", "шесть", "тыс€ч", "тыс€ч", "миллионов", "миллионов"},
            {"7", "семь", "семнадцать", "семьдес€т", "семьсот", "семь", "тыс€ч", "тыс€ч", "миллионов", "миллионов"},
            {"8", "восемь", "восемнадцать", "восемьдес€т", "восемьсот", "восемь", "тыс€ч", "тыс€ч", "миллионов", "миллионов"},
            {"9", "дев€ть", "дев€тнадцать", "дев€носто", "дев€тьсот", "дев€ть", "тыс€ч", "тыс€ч", "миллионов", "миллионов"}
        };
        langUnits = ed;
    }
    
    private void setUkrLang(){
        String[][] ed = {
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
            {"", "один", "два", "три", "четири", 
            "п'€ть", "ш≥сть", "с≥м", "в≥с≥м", "дев'€ть"},
            {"дес€ть", "одинадц€ть", "дванадц€ть", "тринадц€ть",
            "чотирнадц€ть", "п'€тнадц€ть", "ш≥стнадц€ть", "с≥мнадц€ть",
            "в≥с≥мнадц€ть", "дев'€тнадц€ть"},
            {"", "", "двадц€ть", "тридц€ть", "сорок", "п'€тьдес€т",
            "шестдес€т", "с≥мдес€т", "в≥с≥мдес€т", "дев'€носто"},
            {"", "сто", "дв≥ст≥", "триста", "чотириста", "п'€тьсот",
            "ш≥стьсот", "с≥мсот", "в≥с≥мсот", "дев'€тсот"},
            {"", "одна", "дв≥", "три", "чотири", "п'€ть", "ш≥сть", "с≥м",
            "в≥с≥м", "дев'€ть"},
            {"тис€ч", "тис€ча", "тис€ч≥", "тис€ч≥", "тис€ч≥", "тис€ч", "тис€ч",
            "тис€ч", "тис€ч", "тис€ч"},
            {"тис€ч", "тис€ч", "тис€ч", "тис€ч", "тис€ч", "тис€ч", "тис€ч",
            "тис€ч", "тис€ч", "тис€ч"},
            {"м≥льон≥в", "м≥льон", "м≥льона", "м≥льона", "м≥льона",
            "м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в"},
            {"м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в",
            "м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в", "м≥льон≥в"}
        };
        langUnits = ed;
    }
    
    private String transNumber(){
        String retValue;
        int lenValue;// длина строки
        // провер€ем введЄнное значение
        switch (value) {
            case "":
                retValue = "";
                break;
            case "0":
                retValue = "ноль";
                break;
            default:
                // определ€ем длину строки
                lenValue = value.length();
                //                System.out.println("lenValue=" + lenValue);
                // провер€ем длину
                if(lenValue <= 3){
                    // сотни
                    retValue = Hundreds(value, 1, 0);
                }else if(lenValue <= 6){
                    // тыс€чи
                    retValue = Hundreds(value.substring(0, lenValue - 3), 5, 1) +
                            " " + Hundreds(value.substring(lenValue - 3), 1, 0);
                } else if(lenValue <= 9){
                    // миллионы
                    String retHundred = Hundreds(value.substring(lenValue - 3), 
                            1, 0);// сотни
                    
                    String retMillion;
                    String retThousand;
                    /* выполн€ем проверку дл€ тыс€ч: если тыс€чные разр€да не
                    * равны "000", тогда вызываем функцию перевода, иначе
                    * пропускаем этот этап
                    */
                    String subSTR = value.substring(lenValue - 6, lenValue - 3);
//                    System.out.println("subSTR = " + subSTR);
                    if(!subSTR.equals("000")){
                        retThousand = Hundreds(subSTR, 5, 1);
                        retMillion = retThousand + " " + retHundred;// тыс€чи
                    } else {
                        retMillion = retHundred;
                    }
                    retValue = Hundreds(value.substring(0, lenValue - 6), 1, 2) +
                            " " + retMillion;// миллионы
                } else{
                    retValue = "";
                }
                break;
        }
        return retValue;
    }
    
    /**
     * заполнение массива значени€ми
     * @param numCol - номер столбца
     * @param mArray - массив с данными
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
     * @param numString - провер€емое число (строка)
     * @param numCol - номер столбца в массиве langUnits дл€ сравнени€
     * @param numeric - идентификатор (0 - единицы, 1 - тыс€чи, 2 - миллионы)
     * @return возвращает строковый эквавалент числа
     */
    private String Hundreds(String numString, int numCol, int numeric){
        String retval = null;
        int length = numString.length();
//        System.out.println(value);
//        System.out.println("numstring=" + numString + " " + "length=" + length);
        // в зависимости от длины введЄнного числа выбираем
        switch(length){
            case 1:// дл€ однозначных чисел
                retval = oneUnits(numString, numCol, numeric);
                break;
            case 2:// дл€ двузначных чисел
                retval = twoUnits(numString, numCol, numeric);
                break;
            case 3:// дл€ трЄхзначных чисел
                retval = threeUnits(numString, numCol, numeric);
                break;
        }
        return retval;
    }
    
    /**
     * @param numString - провер€емое число (строка)
     * @param numCol - номер столбца в массиве langUnits
     * @param numeric - идентификатор (0 - единицы, 1 - тыс€чи, 2 - миллионы)
     * @return возвращает строковый эквавалент числа дл€ однозначных чисел
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
     * @param numString - провер€емое число (строка)
     * @param numCol - номер столбца в массиве langUnits
     * @param numeric - идентификатор (0 - единицы, 1 - тыс€чи, 2 - миллионы)
     * @return возвращает строковый эквавалент числа дл€ двузначных чисел
     */
    private String twoUnits(String numString, int numCol, int numeric){
        String retval = null;
        int i, j;
        int length = numString.length();
        String subSTR = numString.substring(0, 1);
        
        // определ€ем, с какой цифры начинаетс€ число
        if(subSTR.equals("1")){
            // начинаетс€ с 1
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
            // начинаетс€ с другой цифры
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
     * @param numString - провер€емое число (строка)
     * @param numCol - номер столбца в массиве langUnits
     * @param numeric - идентификатор (0 - единицы, 1 - тыс€чи, 2 - миллионы)
     * @return возвращает строковый эквавалент числа дл€ трЄхзначных чисел
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
                    // определ€ем дес€тки
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
                                    // определ€ем составл€ющие части строки
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
        // вызываем функцию перевода
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
