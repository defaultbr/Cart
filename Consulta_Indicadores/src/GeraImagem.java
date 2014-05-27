    
    import java.awt.*;  
    import java.awt.image.BufferedImage;  
    import java.io.File;  
    import java.io.IOException;  
      
    import javax.imageio.*;  
    import javax.swing.*;  
      
    public class GeraImagem extends Component {  
      
        /**   
            *    
            */  
        public static final long serialVersionUID = -6532574194776055714L;  
        Image img;  
        BufferedImage resizedImage = null;  

      
        public void paint(Graphics g) {  
            g.drawImage(img, 0, 0, null);  
        }  
      
        public GeraImagem(String novolink, tela_principal tela) {  
            try {  
                img = ImageIO.read(new File(novolink));  
                img = resizeImage(img, 850, 1065);  
                
                tela.alteraLabel(img);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
      
        }  
      
        public Dimension getPreferredSize() {  
            if (img == null) {  
                return new Dimension(100, 100);  
            } else {  
                return new Dimension(img.getWidth(null), img.getHeight(null));  
            }  
        }  
      
        // Metodo usado para fazer o resize  
        public Image resizeImage(Image originalImage, int biggerWidth,int biggerHeight) {  
            try {  
                int type = BufferedImage.TYPE_INT_ARGB;  
      
                resizedImage = new BufferedImage(biggerWidth, biggerHeight, type);  
                Graphics2D g = resizedImage.createGraphics();  
      
                g.setComposite(AlphaComposite.Src);  
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
                g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);  
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
      
                g.drawImage(originalImage, 0, 0, biggerWidth, biggerHeight, null);  
                g.dispose();  
            } catch (SecurityException e) {  
                JOptionPane.showMessageDialog(null, "Erro ao converter imagem!",  
                        "Erro", JOptionPane.ERROR_MESSAGE);  
            }  
            return resizedImage;  
        }  
    }  