package juliapianca.bruna.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); // Criação do botão de enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() { // Definição da ação do click do botã
            @Override

            // Obtendo dados digitados pelo usuário
            public void onClick(View v) {

                // Aqui será inserido o endereço de e-mail
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                // Aqui será inserido o assunto do e-mail
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                // Aqui será inserido o conteúdo/texto do e-mail
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); // Ao especificar um ACTION
                // do tipo SENDTO (ENVIAR PARA), o Android procura quais apps instaladas no sistema são
                // capazes de resolver essa ação

                i.setData(Uri.parse("mailto:")); // Esse URI é utilizado para indicar apps que trabalham com envio e
                // recebimento de e-mail.

                String[] emails = new String[]{email}; // Lista de string
                i.putExtra(Intent.EXTRA_EMAIL, emails); // Corresponde a uma lista de strings onde cada string é um e-mail de destinatário
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); // Referente ao campo de assunto
                i.putExtra(Intent.EXTRA_TEXT, texto); // Referente ao corpo de texto do e-mail

                try { // Executar o Intent
                    startActivity(Intent.createChooser(i, "Escolha o APP")); // Aparece para o usuário um
                    // menu de escolher entre apps que são capazes de enviar uma mensagem de e-mail

                } catch (ActivityNotFoundException e) { // Caso não tenha nenhuma app de
                    // e-mail instalada no sistema, uma mensagem de erro é exibida para o usuário
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", // Mensagem de erro
                            Toast.LENGTH_LONG).show(); // Mostra a mensagem de erro em longo tempo
                }
            }
        });
    }
}