#!/usr/bin/python -B

import os


if __name__ == "__main__":

	filename = open("oj","w");
	sl_path = os.getcwd()+"/code/";
	count = 1;
	delimite = "\n========================================\n"
	for file in os.listdir(sl_path):
		if file.endswith(".git"):
			continue
		file = sl_path+file
		filename.write(delimite)
		filename.write("("+str(count)+"). "+file+delimite+"\n"+"\n");
		filename.write(open(file,"r").read())
		filename.write("\n\n")
		count = count+1
	filename.close()
