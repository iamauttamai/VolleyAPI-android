package com.iamauttamai.volleyapi.api;

import android.content.Context;
import android.util.Log;

import com.iamauttamai.volleyapi.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by AuttaphonL. on 15,June,2023
 */

public class CustomCertificate {
    public SSLSocketFactory getCustomCertificate(Context context, String library) {
        SSLSocketFactory sslSocketFactory = null;
        try {
            // Load your custom certificate from the raw resource
            InputStream certInputStream = context.getResources().openRawResource(R.raw.cert_production);

            // Create a TrustManager that includes your custom certificate
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(certInputStream);

            // Create a KeyStore and add your certificate
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("custom_cert", cert);

            // Create a TrustManagerFactory with your KeyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            // Create an SSLContext and initialize it with your TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // Create an SSLSocketFactory from your SSLContext
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            // Handle exception
            // ...
            Log.i("SSLSocketFactory", e.printStackTrace(););
        }
        return sslSocketFactory;
    }
}
