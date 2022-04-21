import "../global.css";
import { NextUIProvider } from "@nextui-org/react";
import { DataContextProvider } from "../store/data-store";

export default function MyApp({ Component, pageProps }) {
  const getLayout = Component.getLayout || ((children) => <>{children}</>);

  return getLayout(
    <NextUIProvider>
      <DataContextProvider>
       <Component {...pageProps} />
      </DataContextProvider>
    </NextUIProvider>
  );
  
}