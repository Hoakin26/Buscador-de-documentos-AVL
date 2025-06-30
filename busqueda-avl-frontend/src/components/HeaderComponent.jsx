import { useState } from 'react';
import { useEffect } from 'react';
import styled from 'styled-components';
import ServicioBusqueda from '../services/ServicioBusqueda';

function HeaderComponent({ setDocumentos, setPalabraBuscada }) {
  const [palabra, setPalabra] = useState("");
  const [scrolled, setScrolled] = useState(false);

  const [tema, setTema] = useState(() => {
    if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
      return "dark";
    }
    return "light";
  });

  useEffect(() => {
    const html = document.documentElement;
    html.setAttribute('data-theme', tema);
    localStorage.setItem('tema', tema);
  }, [tema]);


  const cambiarTema = () => {
    setTema(tema === 'light' ? 'dark' : 'light');
  };

  const buscarDocumento = (e) => {
    console.log("buscando Doc")
    e.preventDefault();
    if (!palabra) {
      setPalabraBuscada(null);
      console.log("sin palabra")
      return;
    }

    ServicioBusqueda.getDocumentosBuscados(palabra).then(response => {

      if (!palabra) {
        console.log("palabra vacia")
        return;
      }

      setDocumentos(response.data)
      setPalabraBuscada(palabra)
    }).catch(error => {
      setPalabraBuscada(null);
      console.log(error);
    })

  }

  useEffect(() => {
    const manejoScroll = () => {
      setScrolled(window.scrollY > 10);
    };

    window.addEventListener("scroll", manejoScroll);
    return () => window.removeEventListener("scroll", manejoScroll);
  }, []);

  return (
    <header className={`sticky top-0 z-50 bg-[#fff] dark:bg-[#101218] grid grid-cols-12 py-5 pl-1 pr-1 transition-shadow duration-300 ${scrolled ? 'shadow-md' : ''}`}>


      <div className='flex items-center md:col-span-1 sm:col-span-1 col-span-2 xl:pl-5 pl-2'>
        {
          tema === 'light' ?
            <img src="/BuhoLupaCafe.webp" alt="BuhoLupa" className="w-13 h-auto max-w-full object-contain" /> :
            <img src="/BuhoLupaBlanco.webp" alt="BuhoLupa" className="w-13 h-auto max-w-full object-contain" />
        }
        <h1 className=" hidden sm:block text-sm sm:text-base font-bold text-[#4F3B1D] dark:text-white">Busqueda</h1>
      </div>


      <div className='flex items-center md:col-span-10 sm:col-span-10 col-span-8 xl:pl-20 sm:pl-30 pl-0'>
        <div className=" lg:w-5/7 sm:w-3/4 w-11/12 relative ">
          <form onSubmit={(e) => buscarDocumento(e)}>
            <input
              value={palabra}
              type="text"
              placeholder="Buscar..."
              className="w-full pl-12 py-2.5 rounded-full bg-[#fff] text-[#1f1f1f] placeholder-[#464645] shadow-[0_2px_5px_rgba(0,0,0,0.15),_0_-2px_5px_rgba(0,0,0,0.1)]
                    dark:bg-[#3F4454] dark:text-white dark:placeholder-gray-100 dark:border-none dark:shadow-none focus:outline-none"
              onChange={(e) => setPalabra(e.target.value)}
            />
          </form>
          <svg className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-[#141414] dark:text-white fill-current pointer-events-none">
            <use xlinkHref='/sprite.svg#lupa' />
          </svg>
        </div>
      </div>

      <StyledWrapper className='col-span-2 md:col-span-1 sm:col-span-1 flex items-center'>
        <div className="toggle-switch w-16">
          <label className="switch-label">
            <input
              type="checkbox"
              className="checkbox"
              onChange={cambiarTema}
              checked={tema === "dark"}
            />
            <span className="slider" />
          </label>
        </div>
      </StyledWrapper>
    </header>
  )
}

const StyledWrapper = styled.div`
  .toggle-switch {
    position: relative;
    width: 50px;
    height: 25px;
    --light: #d8dbe0;
    --dark: #28292c;
    --link: rgb(27, 129, 112);
    --link-hover: rgb(24, 94, 82);
  }

  .switch-label {
    position: absolute;
    width: 100%;
    height: 25px;
    background-color: var(--dark);
    border-radius: 12.5px;
    cursor: pointer;
    border: 2px solid var(--dark);
  }

  .checkbox {
    position: absolute;
    display: none;
  }

  .slider {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 12.5px;
    transition: 0.3s;
  }

  .checkbox:checked ~ .slider {
    background-color: var(--light);
  }

  .slider::before {
    content: "";
    position: absolute;
    top: 4px;
    left: 5px;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    box-shadow: inset 7px -2px 0px 0px var(--light);
    background-color: var(--dark);
    transition: 0.3s;
  }

  .checkbox:checked ~ .slider::before {
    transform: translateX(25px);
    background-color: var(--dark);
    box-shadow: none;
  }`;


export default HeaderComponent