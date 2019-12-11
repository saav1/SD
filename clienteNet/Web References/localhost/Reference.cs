﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// Microsoft.VSDesigner generó automáticamente este código fuente, versión=4.0.30319.42000.
// 
#pragma warning disable 1591

namespace clienteNet.localhost {
    using System;
    using System.Web.Services;
    using System.Diagnostics;
    using System.Web.Services.Protocols;
    using System.Xml.Serialization;
    using System.ComponentModel;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="SensorSoap11Binding", Namespace="http://main")]
    public partial class Sensor : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        private System.Threading.SendOrPostCallback getVolumenOperationCompleted;
        
        private System.Threading.SendOrPostCallback getNombreOperationCompleted;
        
        private System.Threading.SendOrPostCallback setLedOperationCompleted;
        
        private System.Threading.SendOrPostCallback setNombreOperationCompleted;
        
        private System.Threading.SendOrPostCallback getFechaOperationCompleted;
        
        private System.Threading.SendOrPostCallback getLedOperationCompleted;
        
        private System.Threading.SendOrPostCallback readSondaOperationCompleted;
        
        private System.Threading.SendOrPostCallback CallSensorOperationCompleted;
        
        private bool useDefaultCredentialsSetExplicitly;
        
        /// <remarks/>
        public Sensor() {
            this.Url = global::clienteNet.Properties.Settings.Default.clienteNet_localhost_Sensor;
            if ((this.IsLocalFileSystemWebService(this.Url) == true)) {
                this.UseDefaultCredentials = true;
                this.useDefaultCredentialsSetExplicitly = false;
            }
            else {
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        public new string Url {
            get {
                return base.Url;
            }
            set {
                if ((((this.IsLocalFileSystemWebService(base.Url) == true) 
                            && (this.useDefaultCredentialsSetExplicitly == false)) 
                            && (this.IsLocalFileSystemWebService(value) == false))) {
                    base.UseDefaultCredentials = false;
                }
                base.Url = value;
            }
        }
        
        public new bool UseDefaultCredentials {
            get {
                return base.UseDefaultCredentials;
            }
            set {
                base.UseDefaultCredentials = value;
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        /// <remarks/>
        public event getVolumenCompletedEventHandler getVolumenCompleted;
        
        /// <remarks/>
        public event getNombreCompletedEventHandler getNombreCompleted;
        
        /// <remarks/>
        public event setLedCompletedEventHandler setLedCompleted;
        
        /// <remarks/>
        public event setNombreCompletedEventHandler setNombreCompleted;
        
        /// <remarks/>
        public event getFechaCompletedEventHandler getFechaCompleted;
        
        /// <remarks/>
        public event getLedCompletedEventHandler getLedCompleted;
        
        /// <remarks/>
        public event readSondaCompletedEventHandler readSondaCompleted;
        
        /// <remarks/>
        public event CallSensorCompletedEventHandler CallSensorCompleted;
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:getVolumen", RequestNamespace="http://main", ResponseNamespace="http://main", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return")]
        public int getVolumen() {
            object[] results = this.Invoke("getVolumen", new object[0]);
            return ((int)(results[0]));
        }
        
        /// <remarks/>
        public void getVolumenAsync() {
            this.getVolumenAsync(null);
        }
        
        /// <remarks/>
        public void getVolumenAsync(object userState) {
            if ((this.getVolumenOperationCompleted == null)) {
                this.getVolumenOperationCompleted = new System.Threading.SendOrPostCallback(this.OngetVolumenOperationCompleted);
            }
            this.InvokeAsync("getVolumen", new object[0], this.getVolumenOperationCompleted, userState);
        }
        
        private void OngetVolumenOperationCompleted(object arg) {
            if ((this.getVolumenCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.getVolumenCompleted(this, new getVolumenCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:getNombre", RequestNamespace="http://main", ResponseNamespace="http://main", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", IsNullable=true)]
        public string getNombre() {
            object[] results = this.Invoke("getNombre", new object[0]);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public void getNombreAsync() {
            this.getNombreAsync(null);
        }
        
        /// <remarks/>
        public void getNombreAsync(object userState) {
            if ((this.getNombreOperationCompleted == null)) {
                this.getNombreOperationCompleted = new System.Threading.SendOrPostCallback(this.OngetNombreOperationCompleted);
            }
            this.InvokeAsync("getNombre", new object[0], this.getNombreOperationCompleted, userState);
        }
        
        private void OngetNombreOperationCompleted(object arg) {
            if ((this.getNombreCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.getNombreCompleted(this, new getNombreCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:setLed", RequestNamespace="http://main", OneWay=true, Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public void setLed(int led) {
            this.Invoke("setLed", new object[] {
                        led});
        }
        
        /// <remarks/>
        public void setLedAsync(int led) {
            this.setLedAsync(led, null);
        }
        
        /// <remarks/>
        public void setLedAsync(int led, object userState) {
            if ((this.setLedOperationCompleted == null)) {
                this.setLedOperationCompleted = new System.Threading.SendOrPostCallback(this.OnsetLedOperationCompleted);
            }
            this.InvokeAsync("setLed", new object[] {
                        led}, this.setLedOperationCompleted, userState);
        }
        
        private void OnsetLedOperationCompleted(object arg) {
            if ((this.setLedCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.setLedCompleted(this, new System.ComponentModel.AsyncCompletedEventArgs(invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:setNombre", RequestNamespace="http://main", OneWay=true, Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public void setNombre([System.Xml.Serialization.XmlElementAttribute(IsNullable=true)] string nombre) {
            this.Invoke("setNombre", new object[] {
                        nombre});
        }
        
        /// <remarks/>
        public void setNombreAsync(string nombre) {
            this.setNombreAsync(nombre, null);
        }
        
        /// <remarks/>
        public void setNombreAsync(string nombre, object userState) {
            if ((this.setNombreOperationCompleted == null)) {
                this.setNombreOperationCompleted = new System.Threading.SendOrPostCallback(this.OnsetNombreOperationCompleted);
            }
            this.InvokeAsync("setNombre", new object[] {
                        nombre}, this.setNombreOperationCompleted, userState);
        }
        
        private void OnsetNombreOperationCompleted(object arg) {
            if ((this.setNombreCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.setNombreCompleted(this, new System.ComponentModel.AsyncCompletedEventArgs(invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:getFecha", RequestNamespace="http://main", ResponseNamespace="http://main", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", IsNullable=true)]
        public string getFecha() {
            object[] results = this.Invoke("getFecha", new object[0]);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public void getFechaAsync() {
            this.getFechaAsync(null);
        }
        
        /// <remarks/>
        public void getFechaAsync(object userState) {
            if ((this.getFechaOperationCompleted == null)) {
                this.getFechaOperationCompleted = new System.Threading.SendOrPostCallback(this.OngetFechaOperationCompleted);
            }
            this.InvokeAsync("getFecha", new object[0], this.getFechaOperationCompleted, userState);
        }
        
        private void OngetFechaOperationCompleted(object arg) {
            if ((this.getFechaCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.getFechaCompleted(this, new getFechaCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:getLed", RequestNamespace="http://main", ResponseNamespace="http://main", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return")]
        public int getLed() {
            object[] results = this.Invoke("getLed", new object[0]);
            return ((int)(results[0]));
        }
        
        /// <remarks/>
        public void getLedAsync() {
            this.getLedAsync(null);
        }
        
        /// <remarks/>
        public void getLedAsync(object userState) {
            if ((this.getLedOperationCompleted == null)) {
                this.getLedOperationCompleted = new System.Threading.SendOrPostCallback(this.OngetLedOperationCompleted);
            }
            this.InvokeAsync("getLed", new object[0], this.getLedOperationCompleted, userState);
        }
        
        private void OngetLedOperationCompleted(object arg) {
            if ((this.getLedCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.getLedCompleted(this, new getLedCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:readSonda", RequestNamespace="http://main", OneWay=true, Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public void readSonda([System.Xml.Serialization.XmlElementAttribute(IsNullable=true)] string file) {
            this.Invoke("readSonda", new object[] {
                        file});
        }
        
        /// <remarks/>
        public void readSondaAsync(string file) {
            this.readSondaAsync(file, null);
        }
        
        /// <remarks/>
        public void readSondaAsync(string file, object userState) {
            if ((this.readSondaOperationCompleted == null)) {
                this.readSondaOperationCompleted = new System.Threading.SendOrPostCallback(this.OnreadSondaOperationCompleted);
            }
            this.InvokeAsync("readSonda", new object[] {
                        file}, this.readSondaOperationCompleted, userState);
        }
        
        private void OnreadSondaOperationCompleted(object arg) {
            if ((this.readSondaCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.readSondaCompleted(this, new System.ComponentModel.AsyncCompletedEventArgs(invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:Sensor", RequestElementName="Sensor", RequestNamespace="http://main", OneWay=true, Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public void CallSensor() {
            this.Invoke("CallSensor", new object[0]);
        }
        
        /// <remarks/>
        public void CallSensorAsync() {
            this.CallSensorAsync(null);
        }
        
        /// <remarks/>
        public void CallSensorAsync(object userState) {
            if ((this.CallSensorOperationCompleted == null)) {
                this.CallSensorOperationCompleted = new System.Threading.SendOrPostCallback(this.OnCallSensorOperationCompleted);
            }
            this.InvokeAsync("CallSensor", new object[0], this.CallSensorOperationCompleted, userState);
        }
        
        private void OnCallSensorOperationCompleted(object arg) {
            if ((this.CallSensorCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.CallSensorCompleted(this, new System.ComponentModel.AsyncCompletedEventArgs(invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        public new void CancelAsync(object userState) {
            base.CancelAsync(userState);
        }
        
        private bool IsLocalFileSystemWebService(string url) {
            if (((url == null) 
                        || (url == string.Empty))) {
                return false;
            }
            System.Uri wsUri = new System.Uri(url);
            if (((wsUri.Port >= 1024) 
                        && (string.Compare(wsUri.Host, "localHost", System.StringComparison.OrdinalIgnoreCase) == 0))) {
                return true;
            }
            return false;
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void getVolumenCompletedEventHandler(object sender, getVolumenCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class getVolumenCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal getVolumenCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public int Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((int)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void getNombreCompletedEventHandler(object sender, getNombreCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class getNombreCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal getNombreCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public string Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((string)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void setLedCompletedEventHandler(object sender, System.ComponentModel.AsyncCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void setNombreCompletedEventHandler(object sender, System.ComponentModel.AsyncCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void getFechaCompletedEventHandler(object sender, getFechaCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class getFechaCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal getFechaCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public string Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((string)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void getLedCompletedEventHandler(object sender, getLedCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class getLedCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal getLedCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public int Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((int)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void readSondaCompletedEventHandler(object sender, System.ComponentModel.AsyncCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.3752.0")]
    public delegate void CallSensorCompletedEventHandler(object sender, System.ComponentModel.AsyncCompletedEventArgs e);
}

#pragma warning restore 1591