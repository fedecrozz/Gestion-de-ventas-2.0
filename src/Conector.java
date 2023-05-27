import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Conector {
	
	private String path = new File("bd.db").getAbsolutePath();	
	private Connection conexion;
		
	public Connection getConexion() {
		return conexion;
	}
	
	public void setDireccion(String dir) {
		this.path = dir;
	}
	
	public String getDireccion() {
		return this.path;
	}
	
	public void conectar() {
						
		try {
			conexion=DriverManager.getConnection("jdbc:sqlite:"+path);			
		} catch (SQLException ex) {
			System.out.println("No se ha podido establecer conexion: "+ex);
		}
	
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException ex) {
			Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null ,ex);
		}
	}

	public void guardarMovimiento(Movimiento m) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into movimientos(fecha,detalle,monto,tipo,medio_de_pago) values (?,?,?,?,?)");
			
            
            st.setString(1,m.getFecha());
            st.setString(2,m.getDetalle());
            st.setDouble(3,m.getMonto());
            st.setString(4,m.getTipo());
            st.setString(5,m.getMedio_de_pago());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Movimiento> getMovimientos(){
		ArrayList<Movimiento> movimientos= new ArrayList<Movimiento>();
		ResultSet result = null;
		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from movimientos order by fecha DESC");
            result = st.executeQuery();
            
            while (result.next()) {
            	Movimiento p = new Movimiento();
                String fecha = result.getString("fecha");
                String detalle= result.getString("detalle");                
                double monto= result.getDouble("monto");
                String tipo = result.getString("tipo");
                String medio = result.getString("medio_de_pago");
                
                p.setFecha(fecha);
                p.setDetalle(detalle);
                p.setMonto(monto);
                p.setMedio_de_pago(medio);
                p.setTipo(tipo);
                
                movimientos.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return movimientos;
	}
	
	public void borrarMovimiento(String fecha) {
		try {
			PreparedStatement st = conexion.prepareStatement("delete from movimientos where fecha= '"+fecha+"'");
			st.execute();
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public double getMontoBy(String fechaDesde, String fechaHasta, String tipo, String medio) {
		fechaDesde+= " 00:00:00";
		fechaHasta+= " 23:59:59";
		
		double monto = 0;
		
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT sum(monto) FROM movimientos where fecha between '"+fechaDesde+"' and '"+fechaHasta+"' and tipo = '"+tipo+"' and medio_de_pago='"+medio+"'");
            result = st.executeQuery();
            monto =result.getDouble("sum(monto)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return monto;
	}
	
	public double getMontoBy(String fechaDesde, String fechaHasta) {
		fechaDesde+= " 00:00:00";
		fechaHasta+= " 23:59:59";
		
		double monto = 0;
		
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT sum(monto) FROM movimientos where fecha between '"+fechaDesde+"' and '"+fechaHasta+"'");
            result = st.executeQuery();
            monto =result.getDouble("sum(monto)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return monto;
	}
	
	public int getCantidad(String fechaDesde, String fechaHasta, String tipo, String medio) {
		fechaDesde+= " 00:00:00";
		fechaHasta+= " 23:59:59";
		
		int monto = 0;
		
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT count(monto) FROM movimientos where fecha between '"+fechaDesde+"' and '"+fechaHasta+"' and tipo = '"+tipo+"' and medio_de_pago='"+medio+"'");
            result = st.executeQuery();
            monto =result.getInt("count(monto)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return monto;
	}
	
	public int getCantidad(String fechaDesde, String fechaHasta, String tipo) {
		fechaDesde+= " 00:00:00";
		fechaHasta+= " 23:59:59";
		
		int monto = 0;
		
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT count(monto) FROM movimientos where fecha between '"+fechaDesde+"' and '"+fechaHasta+"' and tipo = '"+tipo+"'");
            result = st.executeQuery();
            monto =result.getInt("count(monto)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return monto;
	}
	
	public ArrayList<Cliente>getClientes(){
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		ResultSet result = null;		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from fiados");
            result = st.executeQuery();
            
            while (result.next()) {
            	Cliente p = new Cliente();
            	
                String nombre = result.getString("nombre");              
                double deuda= result.getDouble("deuda");
                
                p.setNombre(nombre);
                p.setDeuda(deuda);
                
                clientes.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return clientes;
	}

	public boolean existeCliente(String nombre) {
		ResultSet result = null;
		boolean ret = false;
		
		try {
			PreparedStatement st = conexion.prepareStatement("select nombre from fiados where nombre = '"+nombre+"'");
			result = st.executeQuery();			
			String Nombre= result.getString("nombre");
            
            if(Nombre.equals(nombre)) {
            	ret=true;
            }
            
		} catch (Exception e) {}
				
		return ret;
	}
	
	public void guardarCliente(Cliente c) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into fiados(nombre,deuda) values (?,?)");
			
            
            st.setString(1,c.getNombre());
            st.setDouble(2,c.getDeuda());
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void borrarCliente(String nombre) {
		try {
			PreparedStatement st = conexion.prepareStatement("delete from fiados where nombre= '"+nombre+"'");
			st.execute();
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void modificarCliente(Cliente c) {
		
		try {
			 PreparedStatement ps = conexion.prepareStatement(
					"update fiados set "+			 
					"nombre = '"+c.getNombre()+"',"+
					"deuda = '"+c.getDeuda()+"'"+
					"WHERE nombre = '"+c.getNombre()+"'"
					 );
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	public Cliente getCliente(String nombre) {
		Cliente c=null;
		ResultSet result = null;
		
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from fiados where nombre = '"+nombre+"'");
			result = st.executeQuery();			
			
            String Nombre = result.getString("nombre");   
            double deuda = result.getDouble("deuda");    
            
            c = new Cliente();         
        	c.setNombre(Nombre);
        	c.setDeuda(deuda);
        	
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return c;
	}
	
	public double getSaldoInicial(String fecha) {
		ResultSet result = null;
		double monto = 0;
		try {			
			PreparedStatement st = conexion.prepareStatement("select * from saldo_inicial where fecha = '"+fecha+"'");
			result = st.executeQuery();			
			   
            monto= result.getDouble("monto");    
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return monto;
	}

	public void modificarSaldoInicial(String fecha, double monto) {
		try {
			 PreparedStatement ps = conexion.prepareStatement(
					"update saldo_inicial set "+			 
					"fecha = '"+fecha+"',"+
					"monto = '"+monto+"'"+
					"WHERE fecha = '"+fecha+"'"
					 );
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void guardarSaldoInicial(String fecha, double monto) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into saldo_inicial(fecha,monto) values (?,?)");
			
            
            st.setString(1,fecha);
            st.setDouble(2,monto);
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public double getSaldoInicial(String fecha_desde, String fecha_hasta) {
		
		double monto = 0;
		
		ResultSet result = null;
		
		try {
            PreparedStatement st = conexion.prepareStatement("SELECT sum(monto) FROM saldo_inicial where fecha between '"+fecha_desde+"' and '"+fecha_hasta+"'");
            result = st.executeQuery();
            monto =result.getInt("sum(monto)");            
        }catch (SQLException e) {
				System.out.println(e);
		}
		
		return monto;
	}

	public void setSeguridad(int activada) {
		try {
			 PreparedStatement ps = conexion.prepareStatement(
					"update ajustes set "+	
					"seguridad_activada = '"+activada+"'"
					 );
			 
				    ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public int getSeguridad() {
		ResultSet result = null;
		int seguridad= 1;
		try {			
			PreparedStatement st = conexion.prepareStatement("select seguridad_activada from ajustes");
			result = st.executeQuery();			
			   
			seguridad= result.getInt("seguridad_activada");    
            
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return seguridad;
	}

	public ArrayList<DeudaDet> getDeudaDet(String Cliente){
		ArrayList<DeudaDet> deudas= new ArrayList<DeudaDet>();
		ResultSet result = null;		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from fiadosdet where cliente = '"+Cliente+"'");
            result = st.executeQuery();
            
            while (result.next()) {
            	DeudaDet p = new DeudaDet();
            	
                String cliente = result.getString("cliente");
                String fecha = result.getString("fecha");    
                double monto= result.getDouble("monto");
                
                p.setCliente(cliente);
                p.setMonto(monto);
                p.setFecha(fecha);
                
                deudas.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return deudas;
	}

	public void agregarDeuda(String cliente, double monto, String fecha) {
		try {
			PreparedStatement st = conexion.prepareStatement("insert into fiadosdet(cliente,fecha,monto) values (?,?,?)");
			
            
            st.setString(1,cliente);
            st.setString(2,fecha);
            st.setDouble(3,monto);
            
            
            st.execute();
            
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void eliminarDeuda(String nombre) {
		try {
			PreparedStatement st = conexion.prepareStatement("delete from fiadosdet where cliente = '"+nombre+"'");
			st.execute();
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public ArrayList<Cliente>getClientes(String busqueda){
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		ResultSet result = null;		
        try {
        		
            PreparedStatement st = conexion.prepareStatement("select * from fiados where nombre LIKE '%" +busqueda+ "%'");
            result = st.executeQuery();
            
            while (result.next()) {
            	Cliente p = new Cliente();
            	
                String nombre = result.getString("nombre");              
                double deuda= result.getDouble("deuda");
                
                p.setNombre(nombre);
                p.setDeuda(deuda);
                
                clientes.add(p);
                
            	}
            
        }catch (SQLException e) {
				System.out.println(e);
		}
       
        return clientes;
	}
	
}

