#include "action.h"
#include <string.h>

void menu() {
	cout << "\n\t < << M A N E J O  D E  L I S T A S >> > \n" << endl;

	cout << "\t \t \t Menu \n" << endl;
	//\t 1. Crear una lista nueva --> Opción no utilizada debido a que cuando se crea un elemento, se crea de una vez espacio en memoria.
	cout << "\t 2. INSERTAR un elemento en la lista \n \n \t \t 3. Localizar un elemento en la lista \n \n \t 4. ELIMINAR un elemento de la lista \n \n \t \t 6. Saber cuantos elementos tiene la lista \n \t \t 7. VER todos los elementos en la lista \n \n \t 5. BUSCAR un elemento en la lista \n \n \t 8. BORRAR toda la lista \n \n \t 9. SALIR. \n" << endl;
		cout << "\t --------------------------------------------------" << endl;
}

void config() {
	system("cls");
	menu();
}

void action(int option) {
	switch (option) {
		case 1:
			//Nodo *inicioLista; 
		   //system("cls");
		   crear();
		   config();
		   cout<<endl<<"\t La lista ha sido creada. \n";
		   break;
		case 2:		
		   insertar();
		   config();	
		   cout<<endl<<"\t El elemento ha sido insertado!. \n";   			   		   
		   break;
		case 3:
		   localizar();	   
		   config();		   
		   break;		   
		case 4:
		   eliminar();
		   config();		   
		   break;
		case 5:
		   buscar();
		   config();		   
		   break;
		case 6:
		   cantElementos();
		   config();		   
		   break;
		case 7:
		   mostrar();
		   config();		   
		   break;
		case 8:
		   borrar();
		   config();		   
		   break;
		case 9:
		   exit(0);			   
		   break;		   
		default:
		   cout << "Opcion invalida" << endl;
	}
}