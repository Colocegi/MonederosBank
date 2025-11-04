    package Modelo;

    import java.util.ArrayList;
    import java.util.List;

    public class Banco {
        private String nombre;
        private String codigo;
        private List<Cliente> listaClientes;
        private List<Cuenta> listaCuentas;

        public Banco(String nombre, String codigo) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.listaClientes = new ArrayList<>();
            this.listaCuentas = new ArrayList<>();
        }

        public void agregarCliente(Cliente cliente) {
            if (!this.listaClientes.contains(cliente)) {
                this.listaClientes.add(cliente);
                // Agrega las cuentas del cliente a la lista global del banco
                for (Cuenta c : cliente.getCuentas()) {
                    if (!this.listaCuentas.contains(c)) {
                        this.listaCuentas.add(c);
                    }
                }
            }
        }

        // Getters para el GestorReportes
        public String getNombre() { return nombre; }
        public String getCodigo() { return codigo; }
        public List<Cliente> getListaClientes() { return listaClientes; }
        public List<Cuenta> getListaCuentas() { return listaCuentas; }
    }