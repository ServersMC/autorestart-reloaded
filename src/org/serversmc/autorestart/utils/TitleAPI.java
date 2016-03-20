package org.serversmc.autorestart.utils;

import org.bukkit.entity.Player;
import org.inventivetalent.reflection.minecraft.Minecraft;
import org.inventivetalent.reflection.resolver.ClassResolver;
import org.inventivetalent.reflection.resolver.ConstructorResolver;
import org.inventivetalent.reflection.resolver.FieldResolver;
import org.inventivetalent.reflection.resolver.MethodResolver;
import org.inventivetalent.reflection.resolver.ResolverQuery;
import org.inventivetalent.reflection.resolver.minecraft.NMSClassResolver;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class TitleAPI
{
  static ClassResolver classResolver = new ClassResolver();
  static NMSClassResolver nmsClassResolver = new NMSClassResolver();
  static Class<?> IChatBaseComponent = nmsClassResolver.resolveSilent(new String[] { "IChatBaseComponent" });
  static Class<?> ChatSerializer = nmsClassResolver.resolveSilent(new String[] { "ChatSerializer", "IChatBaseComponent$ChatSerializer" });
  static Class<?> PacketPlayOutTitle = classResolver.resolveSilent(new String[] { "net.minecraft.server." + Minecraft.getVersion() + "PacketPlayOutTitle", "org.spigotmc.ProtocolInjector$PacketTitle" });
  static Class<?> EnumTitleAction = classResolver.resolveSilent(new String[] { "net.minecraft.server." + Minecraft.getVersion() + "PacketPlayOutTitle$EnumTitleAction", "net.minecraft.server." + Minecraft.getVersion() + "EnumTitleAction", "org.spigotmc.ProtocolInjector$PacketTitle$Action" });
  static Class<?> PlayerConnection = nmsClassResolver.resolveSilent(new String[] { "PlayerConnection" });
  static Class<?> EntityPlayer = nmsClassResolver.resolveSilent(new String[] { "EntityPlayer" });
  static Class<?> NetworkManager = nmsClassResolver.resolveSilent(new String[] { "NetworkManager" });
  static Class<?> Channel = classResolver.resolveSilent(new String[] { "net.minecraft.util.io.netty.channel.Channel", "io.netty.channel.Channel" });
  static ConstructorResolver PacketTitleConstructorResolver = new ConstructorResolver(PacketPlayOutTitle);
  static FieldResolver EntityPlayerFieldResolver = new FieldResolver(EntityPlayer);
  static FieldResolver PlayerConnectionFieldResolver = new FieldResolver(PlayerConnection);
  static FieldResolver NetworkManagerFieldResolver = new FieldResolver(NetworkManager);
  static MethodResolver PlayerConnectionMethodResolver = new MethodResolver(PlayerConnection);
  static MethodResolver ChatSerailizerMethodResolver = new MethodResolver(ChatSerializer);
  static MethodResolver NetworkManagerMethodResolver = new MethodResolver(NetworkManager);
  
  public static void sendTitle(Player player, String json)
  {
    if ((player == null) || (json == null)) {
      throw new IllegalArgumentException("null argument");
    }
    if ((!json.startsWith("{")) || (!json.endsWith("}"))) {
      throw new IllegalArgumentException("invalid json: " + json);
    }
    try
    {
      Object serialized = serialize(json);
      

      Object packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction, IChatBaseComponent } }).newInstance(new Object[] {EnumTitleAction.getEnumConstants()[0], serialized });
      sendPacket(player, packetTitle);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to send Title " + json + " to " + player, e);
    }
  }
  
  public static void sendTitle(Player player, BaseComponent baseComponent)
  {
    if ((player == null) || (baseComponent == null)) {
      throw new IllegalArgumentException("null argument");
    }
    String json = ComponentSerializer.toString(baseComponent);
    sendTitle(player, json);
  }
  
  public static void sendTitle(Player player, BaseComponent baseComponent, int fadeIn, int stay, int fadeOut)
  {
    sendTimings(player, fadeIn, stay, fadeOut);
    sendTitle(player, baseComponent);
  }
  
  public static void sendSubTitle(Player player, String json)
  {
    if ((player == null) || (json == null)) {
      throw new IllegalArgumentException("null argument");
    }
    if ((!json.startsWith("{")) || (!json.endsWith("}"))) {
      throw new IllegalArgumentException("invalid json: " + json);
    }
    try
    {
      Object serialized = serialize(json);
      

      Object packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction, IChatBaseComponent } }).newInstance(new Object[] {EnumTitleAction.getEnumConstants()[1], serialized });
      sendPacket(player, packetTitle);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to send SubTitle " + json + " to " + player, e);
    }
  }
  
  public static void sendSubTitle(Player player, BaseComponent baseComponent)
  {
    if ((player == null) || (baseComponent == null)) {
      throw new IllegalArgumentException("null argument");
    }
    String json = ComponentSerializer.toString(baseComponent);
    sendSubTitle(player, json);
  }
  
  public static void sendSubTitle(Player player, BaseComponent baseComponent, int fadeIn, int stay, int fadeOut)
  {
    sendTimings(player, fadeIn, stay, fadeOut);
    sendSubTitle(player, baseComponent);
  }
  
  public static void sendTimings(Player player, int fadeIn, int stay, int fadeOut)
  {
    if (player == null) {
      throw new IllegalArgumentException("null argument");
    }
    try
    {
      Object packetTitle;
      if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_8_R1)) {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction, Integer.TYPE, Integer.TYPE, Integer.TYPE } }).newInstance(new Object[] { EnumTitleAction.getEnumConstants()[2], Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut) });
      } else {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { Integer.TYPE, Integer.TYPE, Integer.TYPE } }).newInstance(new Object[] { Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut) });
      }
      sendPacket(player, packetTitle);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to send Timings to " + player, e);
    }
  }
  
  public static void clear(Player player)
  {
    if (player == null) {
      throw new IllegalArgumentException("null argument");
    }
    try
    {
      Object packetTitle;
      if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_8_R1)) {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction } }).newInstance(new Object[] { EnumTitleAction.getEnumConstants()[3] });
      } else {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction, IChatBaseComponent } }).newInstance(new Object[] { EnumTitleAction.getEnumConstants()[3], null });
      }
      sendPacket(player, packetTitle);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to send Clear to " + player, e);
    }
  }
  
  public static void reset(Player player)
  {
    if (player == null) {
      throw new IllegalArgumentException("null argument");
    }
    try
    {
      Object packetTitle;
      if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_8_R1)) {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction } }).newInstance(new Object[] { EnumTitleAction.getEnumConstants()[4] });
      } else {
        packetTitle = PacketTitleConstructorResolver.resolve(new Class[][] { { EnumTitleAction, IChatBaseComponent } }).newInstance(new Object[] { EnumTitleAction.getEnumConstants()[4], null });
      }
      sendPacket(player, packetTitle);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to send Reset to " + player, e);
    }
  }
  
  static Object serialize(String json)
    throws ReflectiveOperationException
  {
    return ChatSerailizerMethodResolver.resolve(new ResolverQuery[] { new ResolverQuery("a", new Class[] { String.class }) }).invoke(null, new Object[] { json });
  }
  
  static void sendPacket(Player receiver, Object packet)
    throws ReflectiveOperationException
  {
    Object handle = Minecraft.getHandle(receiver);
    Object connection = EntityPlayerFieldResolver.resolve(new String[] { "playerConnection" }).get(handle);
    PlayerConnectionMethodResolver.resolve(new String[] { "sendPacket" }).invoke(connection, new Object[] { packet });
  }
}
