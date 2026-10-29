import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        JFrame tela = new JFrame("TechStore");

        try {
            URL urlIcone = new URL("https://i.pinimg.com/736x/64/cc/5c/64cc5c1647ab916989d9dd03f430aeb7.jpg");

            ImageIcon img = new ImageIcon(urlIcone);

            tela.setIconImage(img.getImage());
        }
        catch (Exception e) {
            System.out.println("Não foi possível carregar a imagem da internet. Usando ícone padrão.");
        }

        Connection conexao = null;

        try{
            conexao = DriverManager.getConnection("jdbc:postgresql://db.tynyducsqcnkokiszrkc.supabase.co:5432/postgres", "postgres", "itsmeitsverity");
            System.out.println("Conectado com sucesso!");
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        tela.getContentPane().setBackground(Color.decode("#f1f1f1"));

        inicio(tela, conexao);

        tela.setVisible(true);
    }

    public static void inicio(JFrame tela, Connection conexao){
        JPanel tela_inicial = new JPanel();

        tela_inicial.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JLabel titulo_inicio = new JLabel("<html><h1 style='color:black; font-size:50px; font-family:monospace; font-weight:bold;'><u>Tech<span style= 'color:blue;'>Store</span></u></h1></html>");

        titulo_inicio.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'color:black; font-size:20px;'><i>Escolha o que voce deseja fazer!</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(titulo_inicio);
        parte_cima.add(texto);

        JPanel opcoes = new JPanel();

        opcoes.setLayout(new GridLayout(0, 2, 30, 30));

        String html = "<html><div style='font-size:20px; border:none;'>";
        String html_2 = "</div></html>";

        JButton botao_cadastros = new JButton(html + "Produtos cadastrados" + html_2);
        botao_cadastros.setFocusPainted(false);
        botao_cadastros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton botao_cadastrar = new JButton(html + "Cadastrar produtos" + html_2);
        botao_cadastrar.setFocusPainted(false);
        botao_cadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton botao_pesquisar = new JButton(html + "Pesquisar produto" + html_2);
        botao_pesquisar.setFocusPainted(false);
        botao_pesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton botao_apagar = new JButton(html + "Deletar produto" + html_2);
        botao_apagar.setFocusPainted(false);
        botao_apagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton botao_atualizar = new JButton(html+ "Atualizar produto" + html_2);
        botao_atualizar.setFocusPainted(false);
        botao_atualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton botao_estoque = new JButton(html + "Atualizar estoque" + html_2);
        botao_estoque.setFocusPainted(false);
        botao_estoque.setCursor(new Cursor(Cursor.HAND_CURSOR));

        opcoes.add(botao_cadastros);
        opcoes.add(botao_cadastrar);
        opcoes.add(botao_pesquisar);
        opcoes.add(botao_apagar);
        opcoes.add(botao_atualizar);
        opcoes.add(botao_estoque);

        opcoes.setPreferredSize(new Dimension(800, 300));

        JPanel parte_meio = new JPanel();

        parte_meio.setLayout(new GridBagLayout());

        parte_meio.add(opcoes);

        JPanel parte_baixo = new JPanel();

        parte_baixo.setLayout(new BoxLayout(parte_baixo, BoxLayout.Y_AXIS));

        parte_baixo.setPreferredSize(new Dimension(800, 100));

        JLabel traco = new JLabel("<html><div style='color:#f1f1f1; border-bottom:5px solid black; border-color:blue;'>textobemgrande</div></html>");

        traco.setHorizontalAlignment(SwingConstants.CENTER);

        parte_baixo.add(traco);

        JLabel texto_2 = new JLabel("<html><p style= 'font-size:12px;'>©2026 TechStore. Todos os direitos estão reservados</p></html>");

        texto_2.setPreferredSize(new Dimension(800, 80));
        texto_2.setHorizontalAlignment(SwingConstants.CENTER);

        parte_baixo.add(texto_2);

        tela_inicial.add(parte_cima, BorderLayout.NORTH);
        tela_inicial.add(parte_meio, BorderLayout.CENTER);
        tela_inicial.add(parte_baixo, BorderLayout.SOUTH);

        tela.setContentPane(tela_inicial);

        //botoes:

        botao_cadastros.addActionListener(e->{
            ver_tudo(tela, tela_inicial, conexao);
//            JOptionPane.showMessageDialog(tela, "Em breve");
            return;
        });

        botao_cadastrar.addActionListener(e->{
            adicionar_produto(tela, tela_inicial, conexao);
            return;
        });

        botao_pesquisar.addActionListener(e->{
            procurar_produto(tela, tela_inicial, conexao);
            return;
        });

        botao_apagar.addActionListener(e->{
            apagar_produto(tela, tela_inicial, conexao);
            return;
        });

        botao_atualizar.addActionListener(e->{
            atualizar_produto(tela, tela_inicial, conexao);
            return;
        });

        botao_estoque.addActionListener(e->{
            atualizar_estoque(tela, tela_inicial, conexao);
            return;
        });
    }

    public static void ver_tudo(JFrame tela, JPanel tela_inicial, Connection conexao){
        tela_inicial.setVisible(false);

        JPanel tela_mostrar = new JPanel();

        tela_mostrar.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("<html><div style= 'color:blue; font-size:25px;'>←</div></html>");

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("<html><h1 style='color:blue; font-size:40px; font-family:monospace; font-weight:bold;'><u>Geral</u></h1></html>");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'font-size:12px; color:red;'><i>*ATENÇÃO:ver tudo pode demorar ou travar se tiver muitas informações!</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel meio = new JPanel();

        meio.setLayout(new BorderLayout());

        JPanel painel_resultados = new JPanel();
        painel_resultados.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        painel_resultados.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scroll_resultados = new JScrollPane(painel_resultados);
        scroll_resultados.setBorder(null);

        String contar = "SELECT COUNT(*) FROM produtos";

        try(PreparedStatement stmt_total = conexao.prepareStatement(contar);){
            ResultSet resultado = stmt_total.executeQuery();

            if(resultado.next()){
                int quantidade = resultado.getInt(1);

                JLabel total = new JLabel("<html><h3><u><i><b>Total de produtos cadastrados: " + quantidade + "</b></i></u></h3></html>");

                total.setHorizontalAlignment(SwingConstants.CENTER);

                parte_cima.add(total);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(tela, "Houve um erro na hora de puxar a quantidade de produtos.");
        }

        JButton mostrar_tudo = new JButton("<html><h3>Mostrar todos os registros<h3></html>");

        mostrar_tudo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostrar_tudo.setMaximumSize(mostrar_tudo.getPreferredSize());
        mostrar_tudo.setFocusPainted(false);
        mostrar_tudo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel painel_botao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painel_botao.add(mostrar_tudo);

        meio.add(painel_botao, BorderLayout.NORTH);
        meio.add(scroll_resultados, BorderLayout.CENTER);

        voltar.addActionListener(e -> {
            tela_mostrar.setVisible(false);
            inicio(tela, conexao);
        });

        mostrar_tudo.addActionListener(e->{
            String sql = "SELECT * FROM produtos ORDER BY id ASC";

            try (PreparedStatement stmt = conexao.prepareStatement(sql);){
                painel_resultados.removeAll();

                try(ResultSet resultado = stmt.executeQuery();){

                    boolean encontrou = false;

                    while(resultado.next()){
                        encontrou = true;

                        JPanel produto = new JPanel();
                        produto.setLayout(new BoxLayout(produto, BoxLayout.Y_AXIS));

                        produto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE, 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));


                        JLabel id = new JLabel("ID: " + resultado.getInt("id"));

                        JLabel nome = new JLabel("Nome: " + resultado.getString("nome"));

                        JLabel categoria = new JLabel("Categoria: " + resultado.getString("categoria"));

                        JLabel preco = new JLabel("Preço: " + resultado.getDouble("preco"));

                        JLabel quantidade = new JLabel("Quantidade: " + resultado.getInt("quantidade"));

                        produto.add(id);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(nome);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(categoria);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(preco);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(quantidade);

                        painel_resultados.add(produto);
                    }
                    if (!encontrou) {
                        JOptionPane.showMessageDialog(tela, "Nenhum produto encontrado!");
                    }
                }
                catch(SQLException c){
                    c.printStackTrace();
                    JOptionPane.showMessageDialog(tela, "Parece que houve um erro ao mostrar aos produtos.");
                }


            }
            catch (SQLException c) {
                c.printStackTrace();
                JOptionPane.showMessageDialog(tela, "Parece que houve um erro ao pesquisar o produto.");
            }

            painel_resultados.revalidate();
            painel_resultados.repaint();
        });


        tela_mostrar.add(parte_cima, BorderLayout.NORTH);
        tela_mostrar.add(meio, BorderLayout.CENTER);

        tela.setContentPane(tela_mostrar);
        tela.revalidate();
        tela.repaint();

    }

    public static void adicionar_produto(JFrame tela, JPanel tela_inicial, Connection conexao) {
        tela_inicial.setVisible(false);

        JPanel tela_cadastro = new JPanel();

        tela_cadastro.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("<html><div style= 'color:blue; font-size:25px;'>←</div></html>");

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("<html><h1 style='color:blue; font-size:30px; font-family:monospace; font-weight:bold;'><u>Cadastro de produtos</u></h1></html>");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'color:red;'><i>*Preencha todos os campos com seus devidos valores correspondentes</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel parte_meio = new JPanel();

        parte_meio.setLayout(new GridBagLayout());

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(0, 2, 30, 20));

        String html = "<html><h2 style= 'font-family:monospace'><u>";
        String html_2 = "</u></h2></html>";

        JLabel id_texto = new JLabel(html + "ID:" + html_2);
        JTextField id = new JTextField();

        JLabel nome_texto = new JLabel(html + "Nome:" + html_2);
        JTextField nome = new JTextField();

        JLabel categoria_texto = new JLabel(html + "Categoria:" + html_2);
        JTextField categoria = new JTextField();

        JLabel preco_texto = new JLabel(html + "Preço:" + html_2);
        JTextField preco = new JTextField();

        JLabel quantidade_texto = new JLabel(html + "Quantidade:" + html_2);
        JTextField quantidade = new JTextField();

        formulario.add(id_texto);
        formulario.add(id);
        formulario.add(nome_texto);
        formulario.add(nome);
        formulario.add(categoria_texto);
        formulario.add(categoria);
        formulario.add(preco_texto);
        formulario.add(preco);
        formulario.add(quantidade_texto);
        formulario.add(quantidade);

        formulario.setPreferredSize(new Dimension(500, 300));

        parte_meio.add(formulario);

        JPanel parte_baixo = new JPanel();

        parte_baixo.setLayout(new FlowLayout());

        parte_baixo.setPreferredSize(new Dimension(1000, 200));

        JButton botao_cadastrar = new JButton("<html><div style= 'font-size:13px;'>Cadastrar produto</div></html>");

        botao_cadastrar.setFocusPainted(false);
        botao_cadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton botao_apagar = new JButton("<html><div style= 'font-size:13px;'>Apagar campos</div></html>");

        botao_apagar.setFocusPainted(false);
        botao_apagar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        parte_baixo.add(botao_cadastrar);
        parte_baixo.add(botao_apagar);

        tela_cadastro.add(parte_cima, BorderLayout.NORTH);
        tela_cadastro.add(parte_meio, BorderLayout.CENTER);
        tela_cadastro.add(parte_baixo, BorderLayout.SOUTH);

        tela.setContentPane(tela_cadastro);

        voltar.addActionListener(e -> {
            tela_cadastro.setVisible(false);
            inicio(tela, conexao);
        });

        botao_cadastrar.addActionListener(e -> {
            if (id.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || categoria.getText().trim().isEmpty() || preco.getText().trim().isEmpty() || quantidade.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
                return;
            }
            Integer id_campo = null;
            try {
                id_campo = Integer.parseInt(id.getText());
            }
            catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(tela, "Escreva apenas números no ID!");
                return;
            }
            String nome_campo = nome.getText();
            String categoria_campo = categoria.getText();
            Double preco_campo = null;
            try {
                preco_campo = Double.parseDouble(preco.getText());
            } catch (NumberFormatException b) {
                JOptionPane.showMessageDialog(tela, "Escreva apenas números no preço do produto!");
                return;
            }
            Integer quantidade_campo = null;
            try {
                quantidade_campo = Integer.parseInt(quantidade.getText());
            }
            catch (NumberFormatException c) {
                JOptionPane.showMessageDialog(tela, "Escreva apenas números na quantidade do produto!");
                return;
            }

            String sql = "INSERT INTO produtos VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conexao.prepareStatement(sql);

            ) {
                stmt.setInt(1, id_campo);
                stmt.setString(2, nome_campo);
                stmt.setString(3, categoria_campo);
                stmt.setDouble(4, preco_campo);
                stmt.setInt(5, quantidade_campo);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(tela, "Produto cadastrado com sucesso!");

                id.setText("");
                nome.setText("");
                categoria.setText("");
                preco.setText("");
                quantidade.setText("");
            }
            catch (SQLException c) {
                c.printStackTrace();
                JOptionPane.showMessageDialog(tela, "Parece que houve um erro ao cadastrar o produto.");
            }
        });

        botao_apagar.addActionListener(e -> {
            id.setText("");
            nome.setText("");
            categoria.setText("");
            preco.setText("");
            quantidade.setText("");
        });
    }

    public static void procurar_produto(JFrame tela, JPanel tela_inicial, Connection conexao){
        tela_inicial.setVisible(false);

        JPanel tela_procurar = new JPanel();

        tela_procurar.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("<html><div style= 'color:blue; font-size:25px;'>←</div></html>");

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("<html><h1 style='color:blue; font-size:40px; font-family:monospace; font-weight:bold;'><u>Pesquisa de produtos</u></h1></html>");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'font-size:12px;'><i>*Pesquise produtos por ID, nome ou categoria.</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel pesquisar = new JPanel();

        pesquisar.setLayout(new FlowLayout());

        JTextField barra_pesquisa = new JTextField();

        barra_pesquisa.setPreferredSize(new Dimension(500, 50));

        JButton botao_pesquisar = new JButton("<html><div style='font-size:20px;'>⌕</div></html>");

        botao_pesquisar.setFocusPainted(false);
        botao_pesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisar.add(barra_pesquisa);
        pesquisar.add(botao_pesquisar);

        JPanel painel_resultados = new JPanel();
        painel_resultados.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        painel_resultados.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scroll_resultados = new JScrollPane(painel_resultados);
        scroll_resultados.setBorder(null);

        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());

        centro.add(pesquisar, BorderLayout.NORTH);
        centro.add(scroll_resultados, BorderLayout.CENTER);

        tela_procurar.add(parte_cima, BorderLayout.NORTH);
        tela_procurar.add(centro, BorderLayout.CENTER);

        tela.setContentPane(tela_procurar);
        tela.revalidate();
        tela.repaint();

        voltar.addActionListener(e -> {
            tela_procurar.setVisible(false);
            inicio(tela, conexao);
        });

        botao_pesquisar.addActionListener(e->{
            if(barra_pesquisa.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(tela, "Escreva alguma coisa!");
                return;
            }
            painel_resultados.removeAll();

            String campo_pesquisa = barra_pesquisa.getText();

            String sql = "SELECT * FROM produtos WHERE CAST(id AS TEXT) = ? OR nome ILIKE ? OR categoria ILIKE ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql);

            ) {
                stmt.setString(1, campo_pesquisa);
                stmt.setString(2, "%" + campo_pesquisa + "%");
                stmt.setString(3, "%" + campo_pesquisa + "%");

                try(ResultSet resultado = stmt.executeQuery();){
                    boolean encontrou = false;

                    while(resultado.next()){
                        encontrou = true;

                        JPanel produto = new JPanel();
                        produto.setLayout(new BoxLayout(produto, BoxLayout.Y_AXIS));

                        produto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE, 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));


                        JLabel id = new JLabel("ID: " + resultado.getInt("id"));

                        JLabel nome = new JLabel("Nome: " + resultado.getString("nome"));

                        JLabel categoria = new JLabel("Categoria: " + resultado.getString("categoria"));

                        JLabel preco = new JLabel("Preço: " + resultado.getDouble("preco"));

                        JLabel quantidade = new JLabel("Quantidade: " + resultado.getInt("quantidade"));

                        produto.add(id);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(nome);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(categoria);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(preco);
                        produto.add(Box.createVerticalStrut(5));

                        produto.add(quantidade);

                        painel_resultados.add(produto);
                    }
                    if (!encontrou) {
                        JOptionPane.showMessageDialog(tela, "Nenhum produto encontrado!");
                    }
                }
                catch(SQLException c){
                    c.printStackTrace();
                    JOptionPane.showMessageDialog(tela, "Parece que houve um erro ao pesquisar o produto.");
                }


            }
            catch (SQLException c) {
                c.printStackTrace();
                JOptionPane.showMessageDialog(tela, "Parece que houve um erro ao pesquisar o produto.");
            }

            painel_resultados.revalidate();
            painel_resultados.repaint();

        });


    }

    public static void apagar_produto(JFrame tela, JPanel tela_inicial, Connection conexao){
        tela_inicial.setVisible(false);

        JPanel tela_apagar = new JPanel();

        tela_apagar.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("<html><div style= 'color:blue; font-size:25px;'>←</div></html>");

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("<html><h1 style='color:blue; font-size:30px; font-family:monospace; font-weight:bold;'><u>Exclusão de produtos</u></h1></html>");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'color:red;'><i>*Escreva o id do produto para poder apaga-lo.</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel apagar = new JPanel();

        apagar.setLayout(new FlowLayout());

        JTextField barra_apagar = new JTextField();

        barra_apagar.setPreferredSize(new Dimension(500, 50));

        JButton botao_apagar = new JButton("<html><div style='font-size:20px;'>⌫</div></html>");

        botao_apagar.setFocusPainted(false);
        botao_apagar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        apagar.add(barra_apagar);
        apagar.add(botao_apagar);

        tela_apagar.add(parte_cima,BorderLayout.NORTH);
        tela_apagar.add(apagar, BorderLayout.CENTER);

        tela.setContentPane(tela_apagar);

        botao_apagar.addActionListener(e->{
            if (barra_apagar.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Escreva o ID do produto que voce deseja apagar!");
                return;
            }

            int id;

            try {
                id = Integer.parseInt(barra_apagar.getText().trim());
            }
            catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(tela, "Escreva apenas números inteiros(IDs)!");
                return;
            }

            String sql = "DELETE FROM produtos WHERE id = ?";

            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, id);

                int resultado = stmt.executeUpdate();

                if(resultado < 1){
                    JOptionPane.showMessageDialog(tela, "Esse ID não está cadastrado!");
                    return;
                }

                JOptionPane.showMessageDialog(tela, "Produto excluido com sucesso!");
                barra_apagar.setText("");
            }
            catch(SQLException a){
                a.printStackTrace();
                JOptionPane.showMessageDialog(tela, "Erro ao deletar produto");
            }
        });

        voltar.addActionListener(e -> {
            tela_apagar.setVisible(false);
            inicio(tela, conexao);
        });
    }

    public static void atualizar_produto(JFrame tela, JPanel tela_inicial, Connection conexao){
        tela_inicial.setVisible(false);

        JPanel tela_atualizar = new JPanel();

        tela_atualizar.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();

        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("<html><div style= 'color:blue; font-size:25px;'>←</div></html>");

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("<html><h1 style='color:blue; font-size:40px; font-family:monospace; font-weight:bold;'><u>Atualizar produtos</u></h1></html>");

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel("<html><p style= 'font-size:12px;'><i>*Escreva o ID do produto que voce deseja alterar!.</i></p></html>");

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel meio = new JPanel();

        meio.setLayout(new BorderLayout());

        JPanel pesquisa = new JPanel();

        pesquisa.setLayout(new FlowLayout());

        JTextField campo = new JTextField();

        campo.setPreferredSize(new Dimension(500, 50));

        JButton botao_pesquisar = new JButton("<html><div style='font-size:20px;'>⌕</div></html>");

        botao_pesquisar.setFocusPainted(false);
        botao_pesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pesquisa.add(campo);
        pesquisa.add( botao_pesquisar);

        JPanel formulario = new JPanel();

        JPanel botoes = new JPanel();

        botoes.setLayout(new FlowLayout());

        formulario.setLayout(new GridLayout(0, 2, 30, 20));

        JPanel centralizar_formulario = new JPanel(new FlowLayout(FlowLayout.CENTER));


        meio.add(pesquisa, BorderLayout.NORTH);
        meio.add(centralizar_formulario, BorderLayout.CENTER);

        botao_pesquisar.addActionListener(e -> {
            if (campo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Escreva o ID do produto que voce deseja modificar!");
                return;
            }

            int id;

            try {
                id = Integer.parseInt(campo.getText().trim());
            }
            catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(tela, "Escreva apenas números inteiros(IDs)!");
                return;
            }

            String sql = "SELECT * FROM produtos WHERE id = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet resultado = stmt.executeQuery()) {
                    formulario.removeAll();
                    botoes.removeAll();


                    if (resultado.next()) {
                        pesquisa.setVisible(false);

                        String html = "<html><h2 style= 'font-family:monospace'><u>";
                        String html_2 = "</u></h2></html>";

                        JLabel id_texto = new JLabel(html + "ID:" + html_2);
                        JTextField id_novo = new JTextField(resultado.getString("id"));

                        JLabel nome_texto = new JLabel(html + "Nome:" + html_2);
                        JTextField nome_novo = new JTextField(resultado.getString("nome"));

                        JLabel categoria_texto = new JLabel(html + "Categoria:" + html_2);
                        JTextField categoria_nova = new JTextField(resultado.getString("categoria"));

                        JLabel preco_texto = new JLabel(html + "Preço:" + html_2);
                        JTextField preco_novo = new JTextField(resultado.getString("preco"));

                        JLabel quantidade_texto = new JLabel(html + "Quantidade:" + html_2);
                        JTextField quantidade_nova = new JTextField(resultado.getString("quantidade"));

                        formulario.add(id_texto);
                        formulario.add(id_novo);
                        formulario.add(nome_texto);
                        formulario.add(nome_novo);
                        formulario.add(categoria_texto);
                        formulario.add(categoria_nova);
                        formulario.add(preco_texto);
                        formulario.add(preco_novo);
                        formulario.add(quantidade_texto);
                        formulario.add(quantidade_nova);

                        formulario.setPreferredSize(new Dimension(500, 300));

                        centralizar_formulario.add(formulario);

                        JButton botao_atualizar = new JButton("Atualizar produto");
                        botao_atualizar.setFocusPainted(false);
                        botao_atualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

                        JButton botao_apagar = new JButton("Apagar campos");
                        botao_apagar.setFocusPainted(false);
                        botao_apagar.setCursor(new Cursor(Cursor.HAND_CURSOR));

                        botoes.add(botao_atualizar);
                        botoes.add(botao_apagar);

                        botao_atualizar.addActionListener(f->{
                            if (nome_novo.getText().trim().isEmpty() || categoria_nova.getText().trim().isEmpty() || preco_novo.getText().trim().isEmpty() || quantidade_nova.getText().trim().isEmpty() || id_novo.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
                                return;
                            }

                            Integer id_campo = null;

                            try {
                                id_campo = Integer.parseInt(id_novo.getText());
                            }
                            catch (NumberFormatException a) {
                                JOptionPane.showMessageDialog(tela, "Escreva apenas números no ID!");
                                return;
                            }

                            String nome_campo = nome_novo.getText();

                            String categoria_campo = categoria_nova.getText();

                            Double preco_campo = null;

                            try{
                                preco_campo = Double.parseDouble(preco_novo.getText());
                            }
                            catch(NumberFormatException b){
                                JOptionPane.showMessageDialog(tela, "Escreva apenas números no preço!");
                                return;
                            }

                            Integer quantidade_campo = null;

                            try{
                                quantidade_campo = Integer.parseInt(quantidade_nova.getText());
                            }
                            catch(NumberFormatException numberFormatException){
                                JOptionPane.showMessageDialog(tela, "Escreva apenas números na quantidade!");
                                return;
                            }

                            String sql_adicionar = "UPDATE produtos SET id = ?, nome = ?, categoria = ?, preco = ?, quantidade = ? WHERE id = ?";

                            try(PreparedStatement stmt_adicionar = conexao.prepareStatement(sql_adicionar)){
                                stmt_adicionar.setInt(1, id_campo);
                                stmt_adicionar.setString(2, nome_campo);
                                stmt_adicionar.setString(3, categoria_campo);
                                stmt_adicionar.setDouble(4, preco_campo);
                                stmt_adicionar.setInt(5, quantidade_campo);
                                stmt_adicionar.setInt(6, id);


                                stmt_adicionar.executeUpdate();

                                JOptionPane.showMessageDialog(tela, "Produto atualizado com sucesso!");

                                formulario.removeAll();
                                botoes.removeAll();
                                centralizar_formulario.removeAll();

                                pesquisa.setVisible(true);
                                campo.setText("");

                                meio.revalidate();
                                meio.repaint();

                                tela_atualizar.revalidate();
                                tela_atualizar.repaint();
                            }
                            catch(SQLException z){
                                z.printStackTrace();
                                JOptionPane.showMessageDialog(tela, "Erro ao atualizar produto");
                            }

                        });

                        botao_apagar.addActionListener(g->{
                            id_novo.setText("");
                            nome_novo.setText("");
                            categoria_nova.setText("");
                            preco_novo.setText("");
                            quantidade_nova.setText("");

                        });


                        botoes.setPreferredSize(new Dimension(300, 200));

                    }
                    else {
                        JOptionPane.showMessageDialog(tela, "Nenhum produto encontrado com esse ID!");
                        return;
                    }
                }

            }
            catch (SQLException a) {
                a.printStackTrace();
                JOptionPane.showMessageDialog(tela, "Erro ao procurar o produto.");}

            formulario.revalidate();
            formulario.repaint();
        });

        tela_atualizar.add(parte_cima, BorderLayout.NORTH);
        tela_atualizar.add(meio, BorderLayout.CENTER);
        tela_atualizar.add(botoes, BorderLayout.SOUTH);

        tela.setContentPane(tela_atualizar);
        tela.revalidate();
        tela.repaint();

        voltar.addActionListener(e -> {
            tela_atualizar.setVisible(false);
            inicio(tela, conexao);
        });

    }


    public static void atualizar_estoque(JFrame tela, JPanel tela_inicial, Connection conexao) {

        tela_inicial.setVisible(false);

        JPanel tela_atualizar = new JPanel();
        tela_atualizar.setLayout(new BorderLayout(10, 10));

        JPanel parte_cima = new JPanel();
        parte_cima.setLayout(new BoxLayout(parte_cima, BoxLayout.Y_AXIS));

        JButton voltar = new JButton(
                "<html><div style='color:blue; font-size:25px;'>←</div></html>"
        );

        voltar.setBackground(new Color(0, 0, 0, 0));
        voltar.setBorderPainted(false);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setMaximumSize(voltar.getPreferredSize());
        voltar.setContentAreaFilled(false);
        voltar.setOpaque(false);
        voltar.setFocusPainted(false);
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel(
                "<html><h1 style='color:blue; font-size:40px; " +
                        "font-family:monospace; font-weight:bold;'>" +
                        "<u>Atualizar estoque</u></h1></html>"
        );

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel texto = new JLabel(
                "<html><p style='font-size:12px;'>" +
                        "<i>*Escreva o ID do produto que você deseja atualizar o estoque!</i>" +
                        "</p></html>"
        );

        texto.setHorizontalAlignment(SwingConstants.CENTER);

        parte_cima.add(voltar);
        parte_cima.add(titulo);
        parte_cima.add(texto);

        JPanel atualizar = new JPanel(new FlowLayout());

        JTextField barra_atualizar = new JTextField();
        barra_atualizar.setPreferredSize(new Dimension(500, 50));

        JButton botao_atualizar = new JButton(
                "<html><div style='font-size:20px;'>⌕</div></html>"
        );

        botao_atualizar.setFocusPainted(false);
        botao_atualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        atualizar.add(barra_atualizar);
        atualizar.add(botao_atualizar);

        tela_atualizar.add(parte_cima, BorderLayout.NORTH);
        tela_atualizar.add(atualizar, BorderLayout.CENTER);

        tela.setContentPane(tela_atualizar);
        tela.revalidate();
        tela.repaint();

        final int[] idSelecionado = { -1 };

        botao_atualizar.addActionListener(e -> {

            String valor = barra_atualizar.getText().trim();

            if (valor.isEmpty()) {
                JOptionPane.showMessageDialog(
                        tela,
                        idSelecionado[0] == -1
                                ? "Escreva o ID do produto!"
                                : "Escreva a quantidade que deseja adicionar ou retirar!"
                );
                return;
            }

            if (idSelecionado[0] == -1) {

                int id;

                try {
                    id = Integer.parseInt(valor);

                    if (id <= 0) {
                        JOptionPane.showMessageDialog(
                                tela, "O ID deve ser um número inteiro positivo!"
                        );
                        return;
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            tela, "Escreva apenas números inteiros no ID!"
                    );
                    return;
                }

                String sql = "SELECT id FROM produtos WHERE id = ?";

                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

                    stmt.setInt(1, id);

                    try (ResultSet resultado = stmt.executeQuery()) {

                        if (!resultado.next()) {
                            JOptionPane.showMessageDialog(
                                    tela, "Esse ID não está cadastrado!"
                            );
                            barra_atualizar.setText("");
                            barra_atualizar.requestFocus();
                            return;
                        }

                        idSelecionado[0] = id;

                        texto.setText(
                                "<html><p style='font-size:12px;'>" +
                                        "<i>Produto encontrado! Digite a quantidade que deseja " +
                                        "adicionar ou retirar. Use um número negativo para retirar.</i>" +
                                        "</p></html>"
                        );

                        barra_atualizar.setText("");
                        barra_atualizar.requestFocus();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            tela, "Erro ao procurar o produto!"
                    );
                }

            } else {

                int quantidade;

                try {
                    quantidade = Integer.parseInt(valor);

                    if (quantidade == 0) {
                        JOptionPane.showMessageDialog(
                                tela, "Digite uma quantidade diferente de zero!"
                        );
                        return;
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            tela,
                            "Digite apenas números inteiros, como 10 ou -5!"
                    );
                    return;
                }

                String sql =
                        "UPDATE produtos " +
                                "SET quantidade = quantidade + ? " +
                                "WHERE id = ? AND quantidade + ? >= 0";

                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

                    stmt.setInt(1, quantidade);
                    stmt.setInt(2, idSelecionado[0]);
                    stmt.setInt(3, quantidade);

                    int linhas = stmt.executeUpdate();

                    if (linhas > 0) {

                        JOptionPane.showMessageDialog(
                                tela, "Estoque atualizado com sucesso!"
                        );

                        idSelecionado[0] = -1;

                        texto.setText(
                                "<html><p style='font-size:12px;'>" +
                                        "<i>*Escreva o ID do produto que você deseja atualizar o estoque!</i>" +
                                        "</p></html>"
                        );

                        barra_atualizar.setText("");
                        barra_atualizar.requestFocus();

                    } else {

                        JOptionPane.showMessageDialog(
                                tela,
                                "Não foi possível atualizar o estoque. " +
                                        "Verifique se há unidades suficientes para retirar."
                        );

                        barra_atualizar.setText("");
                        barra_atualizar.requestFocus();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            tela, "Erro ao atualizar estoque!"
                    );
                }
            }
        });

        voltar.addActionListener(e -> {
            tela_atualizar.setVisible(false);
            inicio(tela, conexao);
        });
    }


}