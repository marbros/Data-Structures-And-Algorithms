#include <iostream>
#include <stdlib.h>
#include <windows.h>

using namespace std;

struct nodo
{
	char element[13];
	struct nodo *anterior;	
	struct nodo *sgte;
}*firts,*aux,*aux2,*last;

typedef struct nodo Nodo;

Nodo *anterior(Nodo *pos) {
	aux = firts;
	aux2 = aux->sgte;

	while(aux2 != NULL) {
		aux = aux2;
		aux2 = aux2->sgte;
		if(aux2 == pos)
			return aux;
	}
	return NULL;
}

void borrar() {
	aux=firts;
	while(aux != NULL) {
		if(aux == firts) {
			firts = firts->sgte;
		}else {
			last->sgte = aux->sgte;
		}				
		//cout<<"elimino "<< aux->element;
		free(aux);
		last = aux;
		aux = aux->sgte;//c=c+1;		
	}	
	cout<<endl<<">>La lista ha sido borrada!"<<endl;
	aux=firts;
	Sleep(3000);
}

//Función que determina el tamaño del char.
int len(char *S){
	int i = 0;
	while(*(S++)!='\0')
		i++;
	return i;
}

//función booleana que determina si dos char son iguales.
bool sonIguales(char *S1,char *S2){
	if(len(S1)==len(S2)){ 
		for(int i=0;i<len(S1);i++){
			if(S1[i] != S2[i])
				return false;
		}
	}else return false;

	return true;
}	

void buscar() {
	aux = firts;
	char elemt_buscar[13],sw=0;
	int cant = 0;
	cout<<endl<<"=> Ingrese el elemento a buscar...\t";
	cin>>elemt_buscar;
	while(aux != NULL) {
		if(sonIguales(elemt_buscar, aux->element)) {
			sw = 1;
			cant++;
		}
		aux=aux->sgte;
	}

	if(sw==0){
		cout <<endl<<">>Elemento no encontrado";
	}else {
		if(cant == 1) {
			cout <<endl<<"El elemento "<<elemt_buscar<<" se encuentra en la lista "<<cant<<" vez";
		}else {
			cout <<endl<<"El elemento "<<elemt_buscar<<" se encuentra en la lista "<<cant<<" veces";
		}
	}
	Sleep(3000);
}

//gadget que emula un cargador ficticio, durante algunas operaciones.
void cargar() {
	int x;
	int cont = 1;
	while(cont <= 3) {
		for(x=0;x<13;x++) {
			cout<<".";
			Sleep(50);
		}
		cont++;
	}
	cout<<endl;
}

Nodo *crear() {
	cout<<endl<<"\t<<Creando espacio en memoria>>"<<endl;
	cargar();	
    Nodo * List = (Nodo *) malloc (sizeof(Nodo));
    List->anterior = NULL;
    List->sgte = NULL;
    return List; 
}

void cantElementos() {
	aux = firts;
	int nro = 0;
	if(aux == NULL)
		cout << endl << "La lista esta vacia!" << endl;

	while(aux != NULL) {
		nro++;
		aux = aux->sgte;
	}
	cout<<"Se encontraron " <<nro<<" elementos en la lista."<<endl;
	Sleep(3000);
}

void insertar() {

  char content[13]; 
  cout<<"=> Ingrese un elemento a insertar... \t";
  cin >> content;

	Nodo *registroNuevo, *apuntadorAuxiliar;

	registroNuevo = (Nodo *) malloc(sizeof(Nodo));

	if(registroNuevo != NULL) {
		cout<<endl<<"\t<<Insertando Elemento>>"<<endl;
		cargar();		
		//i < len(content) content[i]
	  	for (int i = 0;i < 13; ++i)
	  	{
			registroNuevo->element[i] = content[i];
	  	}				
		registroNuevo->sgte = NULL;

		//Si la lista esta vacia
		if(firts == NULL) {
			firts = registroNuevo;
		}else {
			apuntadorAuxiliar = firts;

			while(apuntadorAuxiliar->sgte != NULL) {
				apuntadorAuxiliar = apuntadorAuxiliar->sgte;
			}
			//Ultimo Nodo
			apuntadorAuxiliar->sgte = registroNuevo;
		}
	}
}

int localizar() {
	aux = firts;
	char elemt_localizar[13],sw = 0;
	cout<<endl<<"=> Ingrese el elemento a localizar...\t";
	cin>>elemt_localizar;	
	int pos = 0, i = 1;
	while(aux != NULL) {
		if(sonIguales(elemt_localizar, aux->element) && sw == 0) {
			pos = i;
			sw = 1;
		}
		i++;
		aux=aux->sgte;
	}
	if(sw == 0) {				
		cout <<endl<<">>Elemento no localizado";	
	}else {
		cout <<endl<<"El elemento "<<elemt_localizar<<" se encuentra en el nodo # "<<pos;	
	}
	Sleep(3000);	
}

void mostrar() {
	aux = firts;
	if(aux == NULL)
		cout << "La lista esta vacia" << endl;

	while(aux != NULL) {
		cout << endl << ">> " << aux->element << endl; 
		aux = aux->sgte;
	}
	Sleep(3000);
}

void eliminar() {
	aux=firts;
	char elemt_eliminar[13];
	int sw=0;
	cout<<endl<<"=> Ingrese un elemento a eliminar... \t";
	cin>>elemt_eliminar;

	while(aux != NULL) {
		if(sonIguales(aux->element, elemt_eliminar) && sw == 0) {
			if(aux == firts) {
				firts = firts->sgte;
			}else {
				last->sgte = aux->sgte;
			}			
			free(aux);
			sw = 1;
		}
		last = aux;
		aux = aux->sgte;//c=c+1;		
	}	
	if(sw == 1){
		cout<<endl<<"\t<<Eliminando Elemento>>"<<endl;
  		cargar();
		cout<<endl<<">>El elemento ha sido eliminado! \n"<<endl;  			
	}else{
		cout<<endl<<"El elemento no ha sido ingresado, en la lista..."<<endl;
		Sleep(5000);
	}
	aux=firts;
	mostrar();
}

Nodo *fin(Nodo *L)
{
    return L->anterior;    
}

Nodo *primero(Nodo *L)
{
    return L->sgte;    
}
