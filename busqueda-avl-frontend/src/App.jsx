import { useEffect, useState } from "react";
import BodyComponent from './components/BodyComponent'
import HeaderComponent from './components/HeaderComponent'
import SeleccionCarpetaComponent from './components/SeleccionCarpetaComponent'
import ServicioBusqueda from "./services/ServicioBusqueda";
import FooterComponent from "./components/FooterComponent";

function App() {

  const [documentos, setDocumentos] = useState([]);
  const [palabraBuscada, setPalabraBuscada] = useState("");
  const [direccion, setDireccion] = useState("");
  const [existeRuta, setExisteRuta] = useState(null);

  useEffect(() => {
    ServicioBusqueda.getRutaArchivo().then(res => {
      setExisteRuta(res.data);
      setDireccion(res.data?.direccion || "");
    }).catch(error => {
      console.log("Error obteniendo ruta inicial:", error);
    });
  }, []);


  return (
    <div className="flex flex-col h-screen bg-[#fff] dark:bg-[#101218]">
      <HeaderComponent
        setDocumentos={setDocumentos}
        setPalabraBuscada={setPalabraBuscada}
      />
      <SeleccionCarpetaComponent
        setDocumentos={setDocumentos}
        direccion={direccion}
        setDireccion={setDireccion}
        existeRuta={existeRuta}
        setExisteRuta={setExisteRuta}
      />
      <BodyComponent
        documentos={documentos}
        setDocumentos={setDocumentos}
        palabraBuscada={palabraBuscada}
        direccion={direccion}
        existeRuta={existeRuta}
      />
      <FooterComponent/>
    </div>
  )
}

export default App
