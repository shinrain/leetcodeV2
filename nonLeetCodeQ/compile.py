#!/usr/bin/python -B

import os


if __name__ == "__main__":

	filename = open("report","w");
	sl_path = os.getcwd()+"/";
	count = 1;
	delimite = "\n========================================\n"
	for file in os.listdir(sl_path):
		if not file.endswith(".java"):
			continue
		file = sl_path+file
		filename.write(delimite)
		filename.write("("+str(count)+"). "+file+delimite+"\n"+"\n");
		filename.write(open(file,"r").read())
		filename.write("\n\n")
		count = count+1
	filename.close()
