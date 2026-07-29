terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.23"
    }
  }
}

provider "kubernetes" {
  config_path = "~/.kube/config"
}

# Kubernetes Deployment for OrderService
resource "kubernetes_deployment" "orderservice" {
  metadata {
    name = "orderservice-deployment"
    labels = {
      app = "orderservice"
    }
  }

  spec {
    replicas = 2 # High-Availability with 2 Pod replicas

    selector {
      match_labels = {
        app = "orderservice"
      }
    }

    template {
      metadata {
        labels = {
          app = "orderservice"
        }
      }

      spec {
        container {
          image             = "devops-orderservice:latest"
          name              = "orderservice"
          image_pull_policy = "Never" # Uses local Docker image built by Jenkins

          port {
            container_port = 8080
          }

          resources {
            limits = {
              cpu    = "500m"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "256Mi"
            }
          }
        }
      }
    }
  }
}

# Kubernetes Service for Internal Load Balancing
resource "kubernetes_service" "orderservice" {
  metadata {
    name = "orderservice-service"
  }

  spec {
    selector = {
      app = kubernetes_deployment.orderservice.spec[0].template[0].metadata[0].labels.app
    }

    port {
      port        = 8080
      target_port = 8080
    }

    type = "NodePort"
  }
}