import com.typesafe.sbt.SbtNativePackager.autoImport.{executableScriptName, packageName}
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport._
import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import com.typesafe.sbt.packager.linux.LinuxPlugin.autoImport.defaultLinuxInstallLocation
import sbt.Keys.version
import sbt.Project


object DockerConfigSettings {
  def get(project: Project) = {

    project.settings(
      packageName in Docker := packageName.value,
      version in Docker := version.value,
      dockerBaseImage := "openjdk:8-jdk-alpine",
      dockerCommands ++= Seq(
        // setting the run script executable
        ExecCmd("RUN",
          "chmod", "u+x",
          s"${(defaultLinuxInstallLocation in Docker).value}/bin/${executableScriptName.value}"),

        Cmd("LABEL", "maintainer", "threeel"),
        Cmd("USER", "daemon"),

        // Setting Enviroment
        Cmd("ENV", "APPLICATION_KEY", "VX2D3UtF6PmVwWpiTByTPH5qZZ7fPFcIXJUXUv55KhgzIGCEvxdlPeoDRTeb9Gqt"),

        // CRM DB Configurations
        Cmd("ENV", "APP_DB_HOST", "db"),
        Cmd("ENV", "APP_DB_NAME", "crm_db"),
        Cmd("ENV", "APP_DB_PORT", "5432"),
        Cmd("ENV", "APP_DB_USERNAME", "postgres"),
        Cmd("ENV", "APP_DB_PASSWORD", "password"),

        // Mail Configuration
        Cmd("ENV", "MAIL_HOST", "smtp.mailtrap.io"),
        Cmd("ENV", "MAIL_PORT", "2525"),
        Cmd("ENV", "MAIL_USER", "username"),
        Cmd("ENV", "MAIL_PASSWORD", "password"),
        Cmd("ENV", "MAIL_SSL", "no"),
        Cmd("ENV", "MAIL_TLS", "no"),
        Cmd("ENV", "MAIL_TLS_REQUIRED", "no"),
        Cmd("ENV", "MAIL_DEBUG", "no"),
        Cmd("ENV", "MAIL_MOCK", "no")
      ),
      dockerExposedPorts := Seq(9000, 9443),
      dockerExposedVolumes := Seq(
        "/data", // Output of Uploaded Files
        "/app/conf", // Configurations of the app
        "/app/logs"
      ),
      dockerUpdateLatest := true,
      dockerRepository := Some("threeel"),
      defaultLinuxInstallLocation in Docker := "/app"
      ,
      dockerEntrypoint in Docker := Seq(
        s"${(defaultLinuxInstallLocation in Docker).value}/bin/${executableScriptName.value}"
      )
    )
  }
}
