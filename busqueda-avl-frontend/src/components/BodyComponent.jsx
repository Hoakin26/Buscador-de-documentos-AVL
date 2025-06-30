import EstadisticasComponent from "./EstadisticasComponent"
import ListaArchivos from "./ListaArchivos"

function BodyComponent({ documentos, setDocumentos, palabraBuscada, direccion, existeRuta }) {
    return (
        <div className="grid grid-cols-12 pb-5 bg-[#fff] dark:bg-[#101218]">
            <div className="md:col-span-7 col-span-12 xl:pl-55 md:pl-20 px-2 sm:pr-0">
                <ListaArchivos
                    documentos={documentos}
                    setDocumentos={setDocumentos}
                    palabraBuscada={palabraBuscada}
                    direccion={direccion}
                    existeRuta={existeRuta}
                />
            </div>
            <div className="md:col-span-1 col-span-0"/>
            <div className="md:col-span-4 col-span-12 pl-2">
                <EstadisticasComponent
                    documentos={documentos}
                />
            </div>
        </div>
    )
}
export default BodyComponent