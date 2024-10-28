# Удобная API для работы с голограммами

## Использование

### Создание голограммы

Для создания новой голограммы используйте метод `createHologram`. Пример кода:

```java
Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);

Location hologramLocation = player.getLocation().add(0, 1, 0);

Hologram hologram = HologramAPIPlugin.getInstance().getHologramManager().createHologram(hologramLocation);
        hologram.addLine("Line 1");
        hologram.addLine("Line 2");
        
for (Player p : players){
    hologram.spawn(ProtocolManager.getInstance(),p);
}
```

### Удаление голограммы

Чтобы удалить голограмму у конкретного игрока, используйте метод `removeHologram`:

```java
Hologram hologram = ...; // Ваш объект Hologram
HologramAPIPlugin.getInstance().getHologramManager().removeHologram(hologram, player);
```

### Очистка всех голограмм

Для удаления всех голограмм у всех игроков при отключении плагина, API автоматически вызовет метод `cleanupAll`, который удаляет все голограммы.

## Методология

### Классы API

- **Hologram**: основной класс для создания и управления голограммами. Содержит методы для спавна, обновления текста и удаления голограмм.
- **HologramManager**: класс-менеджер, который управляет коллекцией голограмм. Позволяет создавать и удалять голограммы.
- **HologramAPIPlugin**: основной класс плагина, который инициализирует API и менеджер голограмм.

### Примеры методов

- `addLine(String line)`: добавляет строку текста к голограмме.
- `spawn(ProtocolManager protocolManager, Player player)`: спавнит голограмму для указанного игрока.
- `updateText(ProtocolManager protocolManager, Player player)`: обновляет текст голограммы для указанного игрока.
- `remove(ProtocolManager protocolManager, Player player)`: удаляет голограмму для указанного игрока.
- `removeAll(ProtocolManager protocolManager)`: удаляет голограмму для всех игроков.

## Зависимости

- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/): библиотека, необходимая для работы с пакетами.
