import axios from "axios";

const URL_API = "http://localhost:8080/documentos";

class ServicioBusqueda{
    getDocumentos(){
        return axios.get(URL_API);
    }

    getDocumentosBuscados(palabra){
        return axios.get(URL_API+"/buscar/"+palabra);
    }

    setRutaArchivo(direccion){
        return axios.post(URL_API, direccion);
    }

    getRutaArchivo(){
        return axios.get(URL_API+"/obtenerRuta")
    }

    getEstadisticas(){
        return axios.get(URL_API+"/obtenerEstadistica")
    }

    abrirDocumento(direccion){
        return axios.post(URL_API+"/abrirWord", direccion)
    }
}

export default new ServicioBusqueda