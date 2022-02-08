import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class CAmain {

	
	static Hashtable<String, Integer> registerFile = new Hashtable<String,Integer>();
    static String Memory[] = new String[2048];
    static Hashtable<String, String> registercode = new Hashtable<String, String>();
    static short pc;
    
    public static void init() {
    	
    	
    	registerFile.put("R0",0);
    	
    	for(int i=1; i<32 ; i++)
    		registerFile.put("R"+i, 0);
    	
    	    	
    	pc = 0;
    	
    	for(int i=0 ; i<32 ; i++) {
    		String s = Integer.toBinaryString(i);
    		while(s.length()<5) {
    			s= "0"+s;
    		}
    		registercode.put("R"+i, s);
    		
    	}
    	
    }
    
    public static void CodeParser(String fileName) throws IOException
	{
    	init();
    	BufferedReader br;
    	
		 br = new BufferedReader(new FileReader(fileName));
		
		  Vector<Vector> s = new Vector<>();
		  String st;
		  
		  Vector<String> binary = new Vector<>();
		  
		  while ((st = br.readLine()) != null)
		  {
			Vector<String> x = new Vector<>();  
		    String [] splited = st.split("\\s+");
		    for(int j=0; j<splited.length ;j++)
				  x.add(splited[j]); 
		    
		    s.add(x);
		  }
		  
		  for(int i=0; i<s.size();i++)
		  {
			  Vector <String> curr = s.get(i);
			  String bin = "";
			  
			  switch(curr.get(0)) {
			  case "ADD": 
			  {
				  bin += "0000";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= registercode.get(curr.get(3));
				  bin+= "0000000000000";
				  binary.add(bin);
				  break;
			  }
			  
			  
			  
			  case "SUB": 
			  {
				  bin += "0001";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= registercode.get(curr.get(3));
				  bin+= "0000000000000";
				  binary.add(bin);
				  break;
			  }
			  
			  case "MUL": 
			  {
				  bin += "0010";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= registercode.get(curr.get(3));
				  bin+= "0000000000000";
				  binary.add(bin);
				  break;
			  }
			  
			  case "MOVI": 
			  {
				  bin += "0011";
      			  bin+= registercode.get(curr.get(1));
				  bin+= "00000";
				  String imm = Integer.toBinaryString(Integer.parseInt(curr.get(2)));
				  while(imm.length()<18) imm = "0" + imm;
				  bin+= imm;
				  binary.add(bin);
				  break;
			  }
			  
			  case "JEQ": 
			  {
				  bin += "0100";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  String imm = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(imm.length()<18) imm = "0" + imm;
				  bin+= imm;
				  binary.add(bin);
				  break;
			  }
			  
			  
			  case "AND": 
			  {
				  bin += "0101";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= registercode.get(curr.get(3));
				  bin+= "0000000000000";
				  binary.add(bin);
				  break;
			  }
			  
			  case "XORI": 
			  {
				  bin += "0110";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  String imm = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(imm.length()<18) imm = "0" + imm;
				  bin+= imm;
				  binary.add(bin);
				  break;
			  }
			  
			  case "JMP": 
			  {
				  bin += "0111";
				  String addrss = Integer.toBinaryString(Integer.parseInt(curr.get(1)));
				  while(addrss.length()<28) addrss = "0" + addrss;
				  bin+= addrss;
				  binary.add(bin);
				  break;
			  }
			  
			  case "LSL": 
			  {
				  bin += "1000";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= "00000";
				  String shamt = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(shamt.length()<13) shamt = "0" + shamt;
				  bin+= shamt;
				  binary.add(bin);
				  break;
			  }
			  
			  case "LSR": 
			  {
				  bin += "1001";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  bin+= "00000";
				  String shamt = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(shamt.length()<13) shamt = "0" + shamt;
				  bin+= shamt;
				  binary.add(bin);
				  break;
			  }
			  
			  case "MOVR": 
			  {
				  bin += "1010";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  String imm = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(imm.length()<18) imm = "0" + imm;
				  bin+= imm;
				  binary.add(bin);
				  break;
			  }
			  
			  case "MOVM": 
			  {
				  bin += "1011";
				  bin+= registercode.get(curr.get(1));
				  bin+= registercode.get(curr.get(2));
				  String imm = Integer.toBinaryString(Integer.parseInt(curr.get(3)));
				  while(imm.length()<18) imm = "0" + imm;
				  bin+= imm;
				  binary.add(bin);
				  break;
			  }
			  
			  
			  
			  
			  
			  }
			  for(int j=0;j<binary.size();j++) {
				  Memory[j] = binary.get(j);
				  
			  }
			  
		  }
		  
		  
	}
    
    
    public static String fetch() {

   
            return Memory[pc++];
           
        
    }
    
    public static Hashtable<String, Integer> Decode(String instruction) {
    	
    	Hashtable <String,Integer>out = new Hashtable<>();
        
        int opcode = 0;  // bits31:27
        int rd = 0;      // bits26:24                                                
        int rs = 0;      // bit23:21
        int rt = 0;      // bits20:18
        int shamt = 0;   // bits17:10
        int funct = 0;   // bits9:0
        int imm = 0;     // bits20:0
        int address = 0; // bits27:0
        
        
        int valueRS = 0;
        int valueRT = 0;
        int valueRD = 0;
        
        
        opcode=Integer.parseInt(instruction.substring(0, 4),2);
        rd=    Integer.parseInt(instruction.substring(4, 9),2);
        rs=    Integer.parseInt(instruction.substring(9, 14),2);
        rt=    Integer.parseInt(instruction.substring(14, 19),2);
        shamt= Integer.parseInt(instruction.substring(19),2);
        
        imm = (int) Long.parseLong(instruction.substring(14),2);
        address=(int) Long.parseLong(instruction.substring(4),2);

         
        valueRS=registerFile.get("R"+rs);
        valueRT=registerFile.get("R"+rt);
        valueRD=registerFile.get("R"+rd);
        
        out.put("opcode",opcode);
        out.put("rs",rs);
        out.put("rt",rt);
        out.put("rd", rd);
        out.put("shamt", shamt);
        out.put("imm", imm);
        out.put("address", address);
        out.put("valueRS", valueRS);
        out.put("insNum", (int) pc);

        
        return out;
         
}
    public static Hashtable<String, Integer> Execute( Hashtable input , int pcs) {
    	
//    	int valueRS, int valueRT, int imm, int ALUop , int Shamt, int pc, int address,
    	
    	
    	int imm = (int) input.get("imm");
    	int ALUop = (int) input.get("opcode");
    	int Shamt = (int) input.get("shamt");
    	int address = (int) input.get("address");
    	
    	System.out.println("ALU op: " + ALUop);
    	
    	
    	
    	Hashtable <String, Integer> out = new Hashtable<>();
    	out.put("zeroFlag", 0);
    	out.put("ALUresult", 0);
    	out.put("pc", pcs);
    	out.put("MemRead", 0);
    	out.put("MemWrite", 0);
    	out.put("MemToReg", 0);
    	out.put("wb", 1);
    	out.put("insNum", (int)input.get("insNum"));
    	out.put("jmp", 0);
    	out.put("jeq", 0);
    	
    	
    	
    	
    	switch(ALUop)
    	{
    	case 0:   out.put("ALUresult", (registerFile.get("R"+input.get("rs"))+registerFile.get("R"+input.get("rt"))));break; 	
    	case 1:  {System.out.println("rs:"+ registerFile.get("R"+input.get("rs"))+"rt: "+registerFile.get("R"+input.get("rt")));
    		out.put("ALUresult", (registerFile.get("R"+input.get("rs"))-registerFile.get("R"+input.get("rt"))));break;
    	}
    	case 2:   out.put("ALUresult", (registerFile.get("R"+input.get("rs"))*registerFile.get("R"+input.get("rt"))));break;
    	case 3:   out.put("ALUresult", imm);break;
    	
    	case 4:   if((registerFile.get("R"+input.get("rd")))==(registerFile.get("R"+input.get("rs"))))
    		      {
    			pc =  (short) (pc+imm);
    			out.put("zeroFlag", 1);
    			out.put("jeq", 1);
    			out.put("wb", 0);
    		      }break;
    		      
    	case 5:   out.put("ALUresult", (registerFile.get("R"+input.get("rs")) & registerFile.get("R"+input.get("rt")))); break;
    	case 6: out.put("ALUresult",(registerFile.get("R"+input.get("rs")) ^ imm));break;
    	case 7:
    	{
    		String s = "";
    		s += Integer.toBinaryString(pc);
    		while(s.length()<32)
    		{
        		s = "0" + s;        			
        	}
    		pc = (short) ((Integer.parseInt((s.substring(0, 4)),2)) | address);
    		out.put("wb", 0);
    		out.put("jmp", 1);
    		
    	}break;
    	
    	case 8: out.put("ALUresult",( registerFile.get("R"+input.get("rs")) << Shamt));break;
    	case 9: out.put("ALUresult",( registerFile.get("R"+input.get("rs")) >> Shamt));break;
    	case 10:{ out.put("ALUresult",( registerFile.get("R"+input.get("rs")) + imm+1024)); out.put("MemRead", 1);out.put("MemToReg", 1);}break;
    	case 11:{ out.put("ALUresult",( registerFile.get("R"+input.get("rs")) + imm+1024));out.put("MemWrite", 1);out.put("wb", 0);}break;
    	
    	}
    	
    	System.out.println("ALU Result: "+ out.get("ALUresult"));
    	
    	
    	
    	if(out.get("ALUresult")==0)
    		out.put("zeroFlag", 1);
    	else out.put("zeroFlag", 0);
    	
   
    	out.put("rd", (Integer) input.get("rd"));
    	
    	
    	
    	
    	return out;
      	
    }
    
    public static Hashtable<String, String> Memory(Hashtable input)
    {
//    	int pc, int ALUresult, int valueRT, int memRead, int memWrite
    	
    	int pc = (int) input.get("pc");
    	int ALUresult = (int) input.get("ALUresult");    	
    	int memRead = (int) input.get("MemRead");
    	int memWrite = (int) input.get("MemWrite");
    	
    	Hashtable<String, String> out = new Hashtable<>();
    	
    	out.put("ALUresult", ""+ALUresult);
    	out.put("readData", "");
    	out.put("MemToReg", ""+input.get("MemToReg"));
    	out.put("wb", ""+input.get("wb"));
    	out.put("insNum", input.get("insNum").toString());
    	
    	
    	
    	
    	if(memRead==1 && memWrite==0)
    	{
    		out.put("readData", Memory[ALUresult]);		
    	}
    	
    	if(memRead==0 && memWrite==1)
    	{
    		int  rd = registerFile.get("R"+input.get("rd"));
        	
    		Memory[ALUresult] = ""+rd;	
    		
    		System.out.println("Memory["+ALUresult+"] = " + rd);
    	}
    	
    	out.put("rd", "" + input.get("rd"));
    
    	return out;
    	    	  	
    }
    
    public static void Writeback(Hashtable input)
    {
//    	int ALUresult, String readData, int MemToReg,String rd, int wb
    	
    	int ALUresult = Integer.parseInt((String) input.get("ALUresult")) ;
    	System.out.println(ALUresult);
    	String readData = (String) input.get("readData");

    	int MemToReg = Integer.parseInt( (String) input.get("MemToReg"));
    	String rd = (String) input.get("rd");
    	int wb = Integer.parseInt( (String) input.get("wb"));
    	
    	
    	
    	System.out.println("rd:"+rd);
    	if(wb==1)
    	{
    		
    	if(MemToReg == 0)
    	{
    		registerFile.put("R"+rd, ALUresult);
    		System.out.println("R"+rd + "= "+ALUresult);
    		
    	}
    	
    	else 
    	{
        	int inData = Integer.parseInt(readData);
    		registerFile.put("R"+rd, inData);
    		System.out.println("R"+rd + "= "+inData);
    	}
    	
    	}
    	
    	registerFile.put("R0",0);
    	
    }
    
    public static void run() {
    	int noIns = 0;
    	for(int i=0; i<Memory.length/2; i++)
			if(Memory[i]!=null)
			noIns++;
    	
    	int cycles =  7 + ((noIns - 1) * 2);
    	
    	boolean goDecode = false;
    	boolean goExecute = false;
    	boolean goMemory = false;
    	boolean goWrite = false;
    	boolean decoding = false;
    	boolean executing = false;
    	boolean noFetch = false;
    	int insCount = 0;
    	int decodeCount = 0;
    	int executeCount = 0;
    	String instruction = "";
    	Hashtable decode = new Hashtable();
    	Hashtable execute = new Hashtable();
    	Hashtable memory = new Hashtable();
    	int decins = 0;
    	
    	
    	for(int i=1 ; i<=cycles ; i++)
    	{
    		System.out.println("cycle: "+i);
    		System.out.println("pc: "+pc);

    		
    		if(pc> noIns) break;
    		
    		if(goWrite)
    		{
    			
    			Writeback(memory);
    			System.out.println("writeback Instruction " + memory.get("insNum"));
    			goWrite = false;
    			noFetch = false;
    		}
    		else if(goMemory)
    		{
    			
    			System.out.println("accessing memory Instruction " + execute.get("insNum"));
    			memory = Memory(execute);
    			System.out.println("input to writeback stage:"+memory);
    			goMemory = false;
    			goWrite = true;
    		}
    		if(executing) { 
    			
    			System.out.println("executing Instruction "+decode.get("insNum"));
    			int currpc = pc;
    			execute = Execute(decode , pc);
    			System.out.println("input to memory stage:"+execute);
    			executing = false;
    			goMemory = true;
    			if(execute.get("jmp").equals(1))
    				{
    				executing = false;
    				decoding = false;
    				goExecute = false;
    				goWrite = false;
    				goDecode = false;
    				
    				if(((int)decode.get("imm"))>currpc) cycles =  7 + (((noIns-(pc - currpc)) - 1) * 2)+2;
    				else
    				cycles +=  7 + (((noIns-((int)decode.get("imm"))) - 1) * 2);
    				
    				insCount = (int)decode.get("imm");
    				noFetch = true;
    				
    				}
    			if(execute.get("jeq").equals(1))
    			{
    				executing = false;
    				decoding = false;
    				goExecute = false;
    				goWrite = false;
    				goDecode = false;
    				
    				if(decode.get("imm").toString().charAt(0) == '-') cycles +=  7 + ((noIns-((int)decode.get("imm")) - 1) * 2);
    				else cycles =  7 + (((noIns-((int)decode.get("imm"))) - 1) * 2)+2;
    				
    				
    				insCount =  pc ;
    				noFetch = true;
    			}
    			    
    			
    			}
    		
    		
    		if(goExecute)
    		{
    			
    			System.out.println("executing Instruction "+decode.get("insNum"));
    			goExecute = false;
    			executing = true;
    			executeCount++;
    			
    		}
    		
    		
    		
    		if(decoding) 
    		{
    			decode = Decode(instruction);
    			System.out.println("input to execute stage:"+decode);
    			decoding = false;
    			goExecute = true;
    		    System.out.println("decoding Instruction "+decins);
    		}
    		
    		
    		
    		 if(goDecode && (!decoding))
    		{
     			System.out.println("decoding Instruction "+decins);
    			goDecode = false;
    			decoding = true;
    			decodeCount++;
    			
    		}

    		if((i%2!=0) && insCount<noIns && (pc<noIns) && !noFetch)
    		{
    			instruction = fetch();
    			System.out.println("fetching Instruction "+pc);
    			insCount++;
    			goDecode = true;
    			decins = pc;
    		}
    		
    		
    		
    		
    		System.out.println("///////////////////////");
    		
    		
    		
    	}
    	
    	System.out.println("Registers: ");
    	System.out.println("PC: "+pc);
    	
    	for(int i= 0 ;i<32; i++)
    		System.out.println("R"+i+": "+registerFile.get("R"+i));
    	
    	registerFile.get("///////////////////////////");
    	
    	System.out.print("Memory: [");
    	for(int i= 0 ;i<2048; i++)
    	{
    		if(i==2047)
    			System.out.print(Memory[i]+"]");
    		
    		else	
    		System.out.print(Memory[i]+", ");
    	}
    	
    	
    	
    	
    	
    	
    	
    }
    
    public static void main(String[] args) throws IOException {
		CodeParser("src/program/Untitled 1" );
     	run();


		
	}
    
    
    
    
    
    
}
