import java.util.ArrayList;
import java.util.HashMap;

public class DNAManipulation {

    // Function to convert DNA string to codons
    public static ArrayList<String> DNAToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<>();
        for (int i = 0; i < dna.length(); i += 3) {
            if (i + 3 <= dna.length()) {
                codons.add(dna.substring(i, i + 3));
            }
        }
        return codons;
    }

    // Function to convert codon to amino acid
    public static String CodonToAminoAcid(String codon) {
        HashMap<String, String> codonMap = new HashMap<>();
        
        // Fill hash map with codons and their corresponding amino acids
        codonMap.put("TTT", "F");
        codonMap.put("TTC", "F");
        codonMap.put("TTA", "L");
        codonMap.put("TTG", "L");
        codonMap.put("CTT", "L");
        codonMap.put("CTC", "L");
        codonMap.put("CTA", "L");
        codonMap.put("CTG", "L");
        codonMap.put("ATT", "I");
        codonMap.put("ATC", "I");
        codonMap.put("ATA", "I");
        codonMap.put("ATG", "M");
        codonMap.put("GTT", "V");
        codonMap.put("GTC", "V");
        codonMap.put("GTA", "V");
        codonMap.put("GTG", "V");
        codonMap.put("TCT", "S");
        codonMap.put("TCC", "S");
        codonMap.put("TCA", "S");
        codonMap.put("TCG", "S");
        codonMap.put("CCT", "P");
        codonMap.put("CCC", "P");
        codonMap.put("CCA", "P");
        codonMap.put("CCG", "P");
        codonMap.put("ACT", "T");
        codonMap.put("ACC", "T");
        codonMap.put("ACA", "T");
        codonMap.put("ACG", "T");
        codonMap.put("GCT", "A");
        codonMap.put("GCC", "A");
        codonMap.put("GCA", "A");
        codonMap.put("GCG", "A");
        codonMap.put("TAT", "Y");
        codonMap.put("TAC", "Y");
        codonMap.put("TAA", "Stop");
        codonMap.put("TAG", "Stop");
        codonMap.put("TGA", "Stop");
        codonMap.put("CAT", "H");
        codonMap.put("CAC", "H");
        codonMap.put("CAA", "Q");
        codonMap.put("CAG", "Q");
        codonMap.put("AAT", "N");
        codonMap.put("AAC", "N");
        codonMap.put("AAA", "K");
        codonMap.put("AAG", "K");
        codonMap.put("GAT", "D");
        codonMap.put("GAC", "D");
        codonMap.put("GAA", "D");
        codonMap.put("GAG", "D");
        codonMap.put("TGT", "C");
        codonMap.put("TGC", "C");
        codonMap.put("TGG", "W");
        codonMap.put("CGT", "R");
        codonMap.put("CGC", "R");
        codonMap.put("CGA", "R");
        codonMap.put("CGG", "R");
        codonMap.put("AGA", "R");
        codonMap.put("AGG", "R");
        codonMap.put("AGT", "S");
        codonMap.put("AGC", "S");
        codonMap.put("GGT", "G");
        codonMap.put("GGC", "G");
        codonMap.put("GGA", "G");
        codonMap.put("GGG", "G");

        return codonMap.getOrDefault(codon, "Unknown");
    }

    // Function to convert DNA to amino acids
    public static ArrayList<String> dna_to_amino_acid(String dna) {
        ArrayList<String> codons = DNAToCodons(dna);
        ArrayList<String> aminoAcids = new ArrayList<>();
        for (String codon : codons) {
            aminoAcids.add(CodonToAminoAcid(codon));
        }
        return aminoAcids;
    }

    // Function to check if two amino acid sequences match
    public static boolean is_match(ArrayList<String> amino_seq1, ArrayList<String> amino_seq2) {
        return amino_seq1.equals(amino_seq2);
    }

    // Main method to execute the program
    public static void main(String[] args) {
        String DNA1 = "CTGATATTGTATCCGGCCGAA";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAG";

        ArrayList<String> amino1 = dna_to_amino_acid(DNA1);
        ArrayList<String> amino2 = dna_to_amino_acid(DNA2);
        ArrayList<String> amino3 = dna_to_amino_acid(DNA3);

        // Compare and print results
        if (is_match(amino1, amino2)) {
            System.out.println("Amino acid sequences of DNA1 and DNA2 are identical.");
        }
        if (is_match(amino1, amino3)) {
            System.out.println("Amino acid sequences of DNA1 and DNA3 are identical.");
        }
        if (is_match(amino2, amino3)) {
            System.out.println("Amino acid sequences of DNA2 and DNA3 are identical.");
        }
    }
}