/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DNK;

/**
 *
 * @author jyk22
 */
public class DNK_functions {

    private final String[][] rules_Encode = {
        //{00,01,10,11}
        {"A", "C", "G", "T"},
        {"A", "C", "T", "G"},
        {"A", "T", "G", "C"},
        {"C", "A", "T", "G"},
        {"C", "A", "G", "T"},
        {"G", "C", "A", "T"},
        {"C", "A", "T", "G"},
        {"C", "T", "A", "G"},};
//    {
//        //{00,01,10,11}
//        {"A", "C", "G", "T"},
//        {"A", "G", "C", "T"},
//        {"T", "C", "G", "A"},
//        {"T", "G", "C", "A"},
//        {"G", "A", "T", "C"},
//        {"G", "T", "A", "C"},
//        {"C", "A", "T", "G"},
//        {"C", "T", "A", "G"},};
    private final String[][] rules_Decode = {
        //{A,C,G,T}
        {"00", "01", "10", "11"},
        {"00", "10", "01", "11"},
        {"11", "01", "10", "00"},
        {"11", "10", "01", "00"},
        {"01", "11", "00", "10"},
        {"10", "11", "00", "01"},
        {"01", "00", "11", "10"},
        {"10", "00", "11", "01"},};

    private String Bin_to_DNA(String dna, int rule) {
        // making 00011011 to ACGT (rule 1) or AGCT (rule 2) ...
        String res = "";
        
        String a;
        if (dna.length() < 8) {
            switch (8 - dna.length()) {
                case 1 ->
                    dna = "0".concat(dna);
                case 2 ->
                    dna = "00".concat(dna);
                case 3 ->
                    dna = "000".concat(dna);
                case 4 ->
                    dna = "0000".concat(dna);
                case 5 ->
                    dna = "00000".concat(dna);
                case 6 ->
                    dna = "000000".concat(dna);
                case 7 ->
                    dna = "0000000".concat(dna);
                default ->
                    System.out.println("error in bin to Dna length ");
            }
        }
        for (int i = 0; i < dna.length(); i = i + 2) {
            a = dna.substring(i, i + 2);
            res += rules_Encode[rule - 1][Integer.parseInt(a, 2)];
        }
        return res;

    }

    private String Dna_To_Complement(String dna) {
        // making ATGC -> TACG
        String res = "";
        for (int i = 0; i < dna.length(); i++) {

            if (dna.charAt(i) == 'T') {
                res += 'A';
            }
            if (dna.charAt(i) == 'A') {
                res += 'T';
            }
            if (dna.charAt(i) == 'C') {
                res += 'G';
            }
            if (dna.charAt(i) == 'G') {
                res += 'C';
            }
        }
        return res;

    }

    private String Complement_to_ReverseComplement(String compl) {
        // ATGC -> CGTA
        
        String res = "";
        for (int i = compl.length() - 1; i >= 0; i--) {
            res += compl.charAt(i);
        }

        return res;

    }

    private String ReverseComplement_to_Bin(String compl, int rule) {
        // making ACGT to 00011011 (rule 1) or 00100111 (rule 2) 
        char Ch;
        String res = "";

        for (int i = 0; i < compl.length(); i++) {
            Ch = compl.charAt(i);
            switch (Ch) {
                case 'A' ->
                    res += rules_Decode[rule - 1][0];
                case 'C' ->
                    res += rules_Decode[rule - 1][1];
                case 'G' ->
                    res += rules_Decode[rule - 1][2];
                case 'T' ->
                    res += rules_Decode[rule - 1][3];
                default ->
                    System.out.println("Error in ReverseComplement_to_Bin ");
            }
        }
        return res;
    }

    public int DnkEncode(int color, int encode_rule) {

        return Integer.parseInt(
                ReverseComplement_to_Bin(
                        Complement_to_ReverseComplement(
                                Dna_To_Complement(
                                        Bin_to_DNA(
                                                Integer.toBinaryString(color & 0xFF)
                                                , encode_rule
                                        )
                                )
                        ), encode_rule
                ), 2
        );
    }

}
