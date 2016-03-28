#include <stdlib.h>
#include <ctype.h>
#include "menu.h"

int main()
{
	menu();
	int opcion;
	char str [3];

	do {	
		cout << endl << ">> Ingrese el numero de la opcion, que desea implementar ... \n" <<endl;
		cout << ">> ";
		cin >> str;
		if(isalpha(str[1])) {
			cout<<"\n La opcion ingresada no es valida \n";	
		}else{
			opcion = atoi(str);	
			action(opcion);
		}
	} while((opcion <= 9) || !(isalpha(str[1])));
	cin.get();
	//system("cls");
	//system("PAUSE");
	return 0;
}	